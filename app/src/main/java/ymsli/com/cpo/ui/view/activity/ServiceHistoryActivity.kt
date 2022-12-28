package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.ObjectKey
import dagger.hilt.android.AndroidEntryPoint
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.AppointmentResult
import ymsli.com.cpo.data.model.MaintenanceList
import ymsli.com.cpo.data.model.VehicleInformationData

import ymsli.com.cpo.databinding.ActivityServiceHistoryBinding
import ymsli.com.cpo.ui.view.adapter.ScheduledAppointmentAdapter
import ymsli.com.cpo.ui.view.adapter.ServiceHistoryAdapter

import ymsli.com.cpo.ui.viewModel.ServiceHistoryViewModel
import ymsli.com.cpo.utils.CancelableToast
import ymsli.com.cpo.utils.Constants
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.Utils


@AndroidEntryPoint
class ServiceHistoryActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityServiceHistoryBinding
    private var activity: Activity = this@ServiceHistoryActivity
    private val tag = "Service_History"
    private val vm: ServiceHistoryViewModel by viewModels()
    private lateinit var vehicleInformationData: VehicleInformationData
    private lateinit var appointmentList: ArrayList<AppointmentResult>
    private lateinit var historyList: ArrayList<MaintenanceList>
    private var viewOnly= "N"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityServiceHistoryBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        initView()
        onClickListener()
        responseObserver()
    }

    override fun onRestart() {
        super.onRestart()
        initView()
        onClickListener()
        responseObserver()
    }

    override fun onResume() {
        super.onResume()
        initView()
        onClickListener()
        responseObserver()
    }

    private fun initView() {

        vehicleInformationData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(
                Constants.VEHICLE_INFORMATION,
                VehicleInformationData::class.java
            )!!
        else
            intent.getSerializableExtra(Constants.VEHICLE_INFORMATION) as VehicleInformationData

        if(intent.getStringExtra("viewOnly")=="y"){
            viewOnly="y"
            viewOnly = intent.getStringExtra("viewOnly").toString()
            mBinding.btnBookAppointment.visibility=GONE
        }

        Log.e("vehicleId",vehicleInformationData.VehicleId)
        vm.serviceHistoryResquest(RequestBodies.GetAppointmentRequestBody(vehicleInformationData.VehicleId))

        mBinding.tvPageHead.text =
            "Yamaha " + vehicleInformationData.Model + " - " + vehicleInformationData.VIN
        mBinding.tvEngine.text = vehicleInformationData.EngineNo
        mBinding.tvChassis.text = vehicleInformationData.ChassisNo
        mBinding.tvEt.text = vehicleInformationData.Displacement
        mBinding.tvAcc.text = vehicleInformationData._0_to_100 + " sec"
        displayImage(vehicleInformationData.DisplayImage1)
        Utils.logThis("Vehicle",vehicleInformationData.toString())
        vm.scheduledAppointmentRequest(
            RequestBodies.GetAppointmentRequestBody(
                vehicleInformationData.VehicleId
            )
        )
    }

    private fun onClickListener() {
        mBinding.ivBack.setOnClickListener {
            finish()
        }
        mBinding.btnBookAppointment.setOnClickListener {
            val intent = Intent(activity, DealerNearActivity::class.java)
            intent.putExtra(Constants.VEHICLE_INFORMATION, vehicleInformationData)
            startActivity(intent)
        }

    }

    private fun displayImage(displayImage: String) {
        Glide.with(activity)
            .load(displayImage)
            .apply(RequestOptions.centerInsideTransform())
            .placeholder(R.drawable.ic_image_black_24dp)
            .error(R.drawable.error_photo)
            .signature(
                ObjectKey(
                    displayImage + System.currentTimeMillis().toString()
                )
            ).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    mBinding.pbProgress.visibility = View.GONE
                    return false;
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    mBinding.pbProgress.visibility = View.GONE
                    return false;
                }
            })
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    @Nullable transition: Transition<in Drawable?>?
                ) {
                    mBinding.ivVehiclePhoto.setBackgroundDrawable(resource)

                }
            })
    }


    private fun responseObserver() {
        vm.serviceHistoryResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar1.visibility = View.GONE
                        response.data?.let {
                            Utils.logThis(tag, "Service History Response is :   ${it.Result}")
                            if (it.Result.isEmpty()) {
                                mBinding.tvNoData1.visibility = View.VISIBLE
                                mBinding.rvHistory.visibility = View.GONE
                            } else {
                                mBinding.tvNoData1.visibility = View.GONE
                                mBinding.rvHistory.visibility = View.VISIBLE
                                historyList=it.Result as ArrayList<MaintenanceList>
                                val adapter= ServiceHistoryAdapter(historyList){_position->
                                        var intent=Intent(applicationContext,ServiceHistoryDetailActivity::class.java)
                                        intent.putExtra("Vin",vehicleInformationData.VIN)
                                        intent.putExtra("Maintenance",historyList[_position])
                                        startActivity(intent)

                                }
                                mBinding.rvHistory.adapter=adapter
                            }
                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar1.visibility = View.GONE
                        response.message?.let { message ->
                            Utils.logThis(tag, "Error message is : $message")
                            if (message == "Unauthorized") {
                                CancelableToast(
                                    activity,
                                    resources.getString(R.string.unauthorized_error)
                                ).alert()
                                Utils.sessionTimeOutStartLoginScreen(activity)
                            } else {
                                CancelableToast(activity, message).error()
                            }
                        }
                    }
                    is Resource.Loading -> {
                        mBinding.progressBar1.visibility = View.VISIBLE
                    }
                }
            }
        })
        vm.scheduledAppointResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = View.GONE
                        response.data?.let {
                            Utils.logThis(tag, "Requested services Response is :   ${it.Result}")
                            if (it.Result.isEmpty()) {
                                mBinding.tvNoData.visibility = View.VISIBLE
                                mBinding.rvAppointment.visibility = View.GONE
                            } else {
                                mBinding.tvNoData.visibility = View.GONE
                                mBinding.rvAppointment.visibility = View.VISIBLE
                                appointmentList = it.Result as ArrayList<AppointmentResult>
                                val adapter =
                                    ScheduledAppointmentAdapter(appointmentList) { _position ->
                                        if(viewOnly!="y"){
                                            if(appointmentList[_position].MaintenanceId!=null){
                                                val intent=Intent(applicationContext,ModifyItemsActivity::class.java)
                                                intent.putExtra(Constants.MAINTENANCE_ID, appointmentList[_position].MaintenanceId.toString())
                                                intent.putExtra(Constants.VEHICLE_INFORMATION, vehicleInformationData)
                                                intent.putExtra(Constants.APPOINTMENT_ID, appointmentList[_position].ServiceRequestId )
                                                startActivity(intent)
                                            }else{
                                                val intent = Intent(applicationContext, ServiceItemsActivity::class.java)
                                                intent.putExtra(Constants.VEHICLE_INFORMATION, vehicleInformationData)
                                                intent.putExtra(Constants.APPOINTMENT_ID, appointmentList[_position].ServiceRequestId )
                                                startActivity(intent)
                                            }
                                        }
                                    }
                                mBinding.rvAppointment.adapter = adapter
                            }

                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = View.GONE
                        response.message?.let { message ->
                            Utils.logThis(tag, "Error message is : $message")
                            if (message == "Unauthorized") {
                                CancelableToast(
                                    activity,
                                    resources.getString(R.string.unauthorized_error)
                                ).alert()
                                Utils.sessionTimeOutStartLoginScreen(activity)
                            } else {
                                CancelableToast(activity, message).error()
                            }
                        }
                    }
                    is Resource.Loading -> {
                        mBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

}