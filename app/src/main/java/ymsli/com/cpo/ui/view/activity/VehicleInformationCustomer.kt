package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.VehicleInformationData
import ymsli.com.cpo.databinding.ActivityVehicleInformationCustomerBinding
import ymsli.com.cpo.ui.viewModel.VehicleInformationViewModel
import ymsli.com.cpo.utils.CancelableToast
import ymsli.com.cpo.utils.Constants
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.Utils


@AndroidEntryPoint
class VehicleInformationCustomer: AppCompatActivity()  {
    private lateinit var vehicleId:String
    private val tag = "Vehicle Information Customer"
    private var activity:Activity=this@VehicleInformationCustomer
    lateinit var binding: ActivityVehicleInformationCustomerBinding
    private lateinit var dialog:Dialog
    private val vm: VehicleInformationViewModel by viewModels()
    private  var nftType = ""
    private lateinit var nftId: String
    private lateinit var vehicleInformationData : VehicleInformationData
    private var viewOnly= "N"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicleInformationCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        initView()
        responseObserver()
        clickListener()
    }

    private fun initView() {
        binding.tvVehicalsInfo.setBackgroundResource(R.drawable.black_cut_background)
        binding.tvVehicalsInfo.setTextColor(Color.parseColor("#FFFFFF"))
        binding.tvVehicalsPhotos.setTextColor(Color.parseColor("#141414"))
        binding.tvVehicalsPhotos.background=null
        if(intent.getStringExtra("vehicleId")!=null) {
            vehicleId = intent.getStringExtra("vehicleId").toString()
            nftType = intent.getStringExtra("NFT_TYPE").toString()
        }
        if(nftType == "" || nftType == "A_NFT")
        {
            binding.functionLayout.visibility = GONE
            binding.tvMint.visibility= VISIBLE
            binding.tvReject.visibility= VISIBLE
            binding.tvNftHead.text= "Approve NFT"
        }
        else
        {
            binding.functionLayout.visibility = VISIBLE
            binding.tvMint.visibility= GONE
            binding.tvReject.visibility= GONE
            binding.tvNftHead.text= "NFT "+intent.getStringExtra("NFT_ID").toString()
        }
        if(intent.getStringExtra("viewOnly")=="y"){
            viewOnly=intent.getStringExtra("viewOnly").toString()
            binding.offerLayout.visibility= GONE
            binding.nftQrLayout.visibility= GONE
            binding.clViewOnly.visibility= VISIBLE
            binding.clNormalButtons.visibility= GONE
        }
        vm.vehicleInformationRequest(RequestBodies.VehicleInformationBody(vehicleId))
    }

    private fun clickListener() {

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.tvVehicalsInfo.setOnClickListener(View.OnClickListener {
            binding.tvVehicalsInfo.setBackgroundResource(R.drawable.black_cut_background);
            binding.tvVehicalsInfo.setTextColor(Color.parseColor("#FFFFFF"))
            binding.tvVehicalsPhotos.setTextColor(Color.parseColor("#141414"))
            binding.tvVehicalsPhotos.background=null;
            findNavController(R.id.fragmentCustomerContainerView).navigate(R.id.fragmentVehicleInformationCustomer)

        })

        binding.tvVehicalsPhotos.setOnClickListener(View.OnClickListener {
            binding.tvVehicalsPhotos.setBackgroundResource(R.drawable.black_cut_background);
            binding.tvVehicalsPhotos.setTextColor(Color.parseColor("#FFFFFF"))
            binding.tvVehicalsInfo.setTextColor(Color.parseColor("#141414"))
            binding.tvVehicalsInfo.background=null;
            findNavController(R.id.fragmentCustomerContainerView).navigate(R.id.fragmentVehiclePhotosCustomer)
        })

        binding.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.tvMint.setOnClickListener(View.OnClickListener {
            vm.mintNftRequest(
                RequestBodies.MintNftRequestBody(
                    vehicleId
                )
            )
        })
        binding.tvReject.setOnClickListener(View.OnClickListener {
            showDialog()
        })

        binding.serviceLayout.setOnClickListener {
            val intent = Intent(activity, ServiceHistoryActivity::class.java)
            intent.putExtra(Constants.VEHICLE_INFORMATION,vehicleInformationData)
            intent.putExtra("viewOnly",viewOnly)
            startActivity(intent)
        }

        binding.certificationLayout.setOnClickListener {
            val intent = Intent(activity, AppraisalHistoryActivity::class.java)
            intent.putExtra(Constants.VEHICLE_INFORMATION,vehicleInformationData)
            intent.putExtra("viewOnly",viewOnly)
            startActivity(intent)
        }

        binding.nftQrLayout.setOnClickListener {
            val intent = Intent(activity,QrCodeActivity::class.java)
            intent.putExtra(Constants.VEHICLE_ID,vehicleInformationData.VehicleId)
            startActivity(intent)
        }

        binding.tvOffer.setOnClickListener {
            if(binding.etPrice.text.toString()!=""){
                vm.submitOfferRequest(RequestBodies.SubmitOfferPriceRequest(vehicleId,binding.etPrice.text.toString().toInt()))
            }else{
                Toasty.warning(activity,"Please enter price")
            }
        }
        binding.offerLayout.setOnClickListener {
            var intent=Intent(activity,OfferListActivity::class.java)
            intent.putExtra(Constants.VEHICLE_ID,vehicleId)
            intent.putExtra(Constants.HEADER,"Yamaha "+vehicleInformationData.Model+" - "+vehicleInformationData.VIN)
            startActivity(intent)
        }
    }


    private fun responseObserver() {
        vm.vehicleInformationResponse.observe(this, Observer { event->
            event.getContentIfNotHandled()?.let { response->
                when(response){
                    is Resource.Success->{
                        binding.progressBar.visibility= GONE
                        binding.llScreen.visibility=VISIBLE
                        response.data?.let {
                            Utils.logThis(tag, "Vehicle Information Response  is :   ${it.Result}")
                            binding.item=it.Result
                            vehicleInformationData = it.Result
                            binding.executePendingBindings()
                            if(vehicleInformationData.NftId!=null){
                                binding.tvNftHead.text= "NFT "+ vehicleInformationData.NftId.toString().substring(0,vehicleInformationData.NftId.toString().indexOf("."))
                            }

                            Utils.setImage(activity,it.Result.DisplayImage,binding.ivVehiclePhoto)
                        }
                    }is Resource.Error -> {
                    binding.progressBar.visibility = GONE
                    response.message?.let { message ->
                        Utils.logThis(tag, "Error message is : $message")
                        if (message == "Unauthorized") {
                            CancelableToast(activity, resources.getString(R.string.unauthorized_error)).alert()
                            Utils.sessionTimeOutStartLoginScreen(activity)
                        } else {
                            CancelableToast(activity,message).error()
                        }
                    }
                }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = VISIBLE
                        binding.llScreen.visibility=GONE
                    }
                }
            }
        })


        vm.mintNftResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = GONE
                        response.data?.let { responseData ->
                            val message = responseData.SuccessMessage.toString()
                            val intent= Intent(activity, MintNftSuccessActivity::class.java)
                            intent.putExtra(Constants.MESSAGE,message)
                            intent.putExtra(Constants.TX_HASH,responseData.Result.TxHash)
                            intent.putExtra(Constants.TX_URL,responseData.Result.TxHashUrl)
                            startActivity(intent)
                            finish()
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = GONE
                        response.message?.let { responseData ->
                            val message = responseData
                            CancelableToast(activity, message).error()
                        }
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = VISIBLE
                    }
                }
            }
        })

        vm.submitOfferResponse.observe(this, Observer {event->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = GONE
                        response.data?.let { responseData ->
                            CancelableToast(activity,"Your offer has been placed successfully").success()
                        }
                    }
                    is Resource.Error -> {
                    binding.progressBar.visibility = GONE
                    response.message?.let { responseData ->
                        val message = responseData
                        CancelableToast(activity, message).error()
                    }
                }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = VISIBLE
                    }
                }
            }
        })

    }


    private fun showDialog() {
        dialog= Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_reject);
        val yesBtn=dialog.findViewById(R.id.tvSubmit) as TextView
        val noBtn=dialog.findViewById(R.id.tvCancel) as TextView
        yesBtn.setOnClickListener { dialog.dismiss() }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }

    override fun onBackPressed() {
        finish()
    }

}