package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
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
import timber.log.Timber.Forest.tag
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.AppraisalDetailModel
import ymsli.com.cpo.data.model.AppraisalHistoryResult
import ymsli.com.cpo.data.model.VehicleInformationData
import ymsli.com.cpo.databinding.ActivityAppraisalHistoryBinding
import ymsli.com.cpo.ui.view.adapter.AppraisalHistoryAdapter
import ymsli.com.cpo.ui.viewModel.AppraisalHistoryViewModel
import ymsli.com.cpo.utils.CancelableToast
import ymsli.com.cpo.utils.Constants
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.Utils

@AndroidEntryPoint
class AppraisalHistoryActivity : AppCompatActivity() {
    private lateinit var vehicleId:String
    private var activity: Activity =this@AppraisalHistoryActivity
    lateinit var binding: ActivityAppraisalHistoryBinding
    private val vm: AppraisalHistoryViewModel by viewModels()
    private lateinit var vehicleInformationData : VehicleInformationData
    private lateinit var appraisalHistoryList : ArrayList<AppraisalHistoryResult>
    private lateinit var appraisalDetail: AppraisalDetailModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAppraisalHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        initView()
        responseObservers()
        clickObserver()
    }

    private fun clickObserver() {
        binding.ivBack.setOnClickListener {
            finish()
        }
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
            binding.btnRequestAppraisal.visibility=GONE
        }

        binding.tvPageHead.text =
            "Yamaha " + vehicleInformationData.Model + " - " + vehicleInformationData.VIN
        binding.tvEngine.text = vehicleInformationData.EngineNo
        binding.tvChassis.text = vehicleInformationData.ChassisNo
        binding.tvEt.text = vehicleInformationData.Displacement
        binding.tvAcc.text = vehicleInformationData._0_to_100 + " sec"
        displayImage(vehicleInformationData.DisplayImage1)
        Utils.logThis("Vehicle",vehicleInformationData.toString())

        vm.getAppraisalHistory(RequestBodies.VehicleInformationBody(vehicleInformationData.VehicleId))
    }

    private fun responseObservers() {
        vm.appraisalHistory.observe(this, Observer { event->
            event.getContentIfNotHandled()?.let { response->
                when(response){
                    is Resource.Success -> {
                        binding.pbProgressBar.visibility=GONE
                        response.data?.let { it ->
                            Log.e("Appraisal History Response: ", it.Result.toString())
                            if (it.Result.isEmpty()) {
                                binding.rvAppraisalHistory.visibility = GONE
                                binding.tvNoData.visibility= VISIBLE
                            }else{
                                binding.rvAppraisalHistory.visibility = VISIBLE
                                binding.tvNoData.visibility= GONE
                                appraisalHistoryList= it.Result as ArrayList<AppraisalHistoryResult>
                                val adapter = AppraisalHistoryAdapter(activity,appraisalHistoryList){ position->
                                    appraisalDetail=it.Result[position].AppraisalDetailModel
                                    var intent = Intent(activity,AppraisalDetailActivity::class.java)
                                    intent.putExtra(Constants.APPRAISAL_DETAIL,appraisalDetail)
                                    intent.putExtra(Constants.VEHICLE_IMAGE,vehicleInformationData.DisplayImage)
                                    startActivity(intent)
                                }
                                binding.rvAppraisalHistory.adapter=adapter
                            }
                        }
                    }
                    is Resource.Error -> {
                        binding.pbProgressBar.visibility = View.GONE
                        response.message?.let { message ->
                            //Utils.logThis(tag, "Error message is : $message")
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
                        binding.pbProgressBar.visibility = View.VISIBLE
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
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.pbProgress.visibility = View.GONE
                    return false;
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.pbProgress.visibility = View.GONE
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
                    binding.ivVehiclePhoto.setBackgroundDrawable(resource)
                }
            })
    }
}