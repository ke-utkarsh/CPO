package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
import ymsli.com.cpo.data.model.DealersResult
import ymsli.com.cpo.data.model.VehicleInformationData
import ymsli.com.cpo.databinding.ActivityDealersNearBinding
import ymsli.com.cpo.ui.view.adapter.DealerNearAdapter
import ymsli.com.cpo.ui.viewModel.DealerNearViewModel
import ymsli.com.cpo.utils.CancelableToast
import ymsli.com.cpo.utils.Constants
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.Utils

@AndroidEntryPoint
class DealerNearActivity: AppCompatActivity() {
    private lateinit var mBinding : ActivityDealersNearBinding
    private var activity: Activity =this@DealerNearActivity
    private val vm: DealerNearViewModel by viewModels()
    private val tag="Dealer's Near Me"
    private var dealerList=ArrayList<DealersResult>()
    private lateinit var vehicleInformationData: VehicleInformationData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =ActivityDealersNearBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.hide()
        mBinding.rvDealers.layoutManager= LinearLayoutManager(this)
        initView()
        responseObserver()
        setOnClickListener()
    }

    private fun initView() {
        vm.dealersNearRequest()
        vehicleInformationData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(
                Constants.VEHICLE_INFORMATION,
                VehicleInformationData::class.java
            )!!
        else
            intent.getSerializableExtra(Constants.VEHICLE_INFORMATION) as VehicleInformationData

        mBinding.tvPageHead.text = "Yamaha " + vehicleInformationData.Model + " - " + vehicleInformationData.VIN
        mBinding.tvEngine.text = vehicleInformationData.EngineNo
        mBinding.tvChassis.text = vehicleInformationData.ChassisNo
        mBinding.tvEt.text = vehicleInformationData.Displacement
        mBinding.tvAcc.text = vehicleInformationData._0_to_100 + " sec"
        displayImage(vehicleInformationData.DisplayImage1)

    }

    private fun setOnClickListener() {
        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }


    private fun responseObserver() {
        vm.dealersNearResponse.observe(this, Observer { event->
            event.getContentIfNotHandled()?.let { response->
                when(response){
                    is Resource.Success -> {
                        mBinding.pbProgressBar.visibility= View.GONE
                        mBinding.tvNoDealer.visibility=View.GONE
                        response.data?.let {
                            Utils.logThis(tag, "Dealer's Near Me Response :   ${it.Result}")
                            dealerList=it.Result as ArrayList<DealersResult>
                            if(dealerList.size==0){
                                mBinding.tvNoDealer.visibility=View.VISIBLE
                            }else{
                                val adapter=DealerNearAdapter(dealerList){ clickedPosition ->
                                    val intent = Intent(applicationContext, BookAppointmentActivity::class.java)
                                    intent.putExtra(Constants.VEHICLE_INFORMATION, vehicleInformationData)
                                    intent.putExtra(Constants.DEALER_INFORMATION, dealerList[clickedPosition])
                                    startActivity(intent)
                                    finish()
                                }
                                mBinding.rvDealers.adapter=adapter
                            }
                        }
                    }
                    is Resource.Error->{
                        mBinding.pbProgressBar.visibility = View.GONE
                        response.message?.let { message ->
                            Utils.logThis(tag, "Error message is : $message")
                            if (message == "Unauthorized") {
                                CancelableToast(activity, resources.getString(R.string.unauthorized_error)).alert()
                                Utils.sessionTimeOutStartLoginScreen(activity)
                            } else {
                                CancelableToast(activity,message).error()
                            }
                        }
                        mBinding.tvNoDealer.visibility=View.VISIBLE
                    }
                    is Resource.Loading -> {
                        mBinding.pbProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
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
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    mBinding.pbProgress.visibility= View.GONE
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    mBinding.pbProgress.visibility= View.GONE
                    return false
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
}
