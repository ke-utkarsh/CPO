package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.databinding.ActivityVehicalInformationBinding
import ymsli.com.cpo.ui.viewModel.VehicleInformationViewModel
import ymsli.com.cpo.utils.CancelableToast
import ymsli.com.cpo.utils.Constants
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.Utils

@AndroidEntryPoint
class VehiclesInformationDealer : AppCompatActivity() {
    lateinit var binding:ActivityVehicalInformationBinding
    private val vm:VehicleInformationViewModel by viewModels()
    private lateinit var vehicleId:String
    private val tag = "Vehicle Information"
    private var activity:Activity=this@VehiclesInformationDealer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVehicalInformationBinding.inflate(layoutInflater)
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
        if(intent.hasExtra(Constants.VEHICLE_ID)) {
            vehicleId = intent.getStringExtra(Constants.VEHICLE_ID).toString()
        }
        else
        {
            return
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
            findNavController(R.id.fragmentContainerView).navigate(R.id.fragmentVehicalInformation)

        })

        binding.tvVehicalsPhotos.setOnClickListener(View.OnClickListener {

            binding.tvVehicalsPhotos.setBackgroundResource(R.drawable.black_cut_background);
            binding.tvVehicalsPhotos.setTextColor(Color.parseColor("#FFFFFF"))
            binding.tvVehicalsInfo.setTextColor(Color.parseColor("#141414"))
            binding.tvVehicalsInfo.background=null
            findNavController(R.id.fragmentContainerView).navigate(R.id.fragmentVehicalPhotos)
        })
    }

    private fun responseObserver() {

        vm._nextEventClick.observe(this, Observer {
            if(it)
            {
                binding.tvVehicalsPhotos.setBackgroundResource(R.drawable.black_cut_background);
                binding.tvVehicalsPhotos.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tvVehicalsInfo.setTextColor(Color.parseColor("#141414"))
                binding.tvVehicalsInfo.background=null
                findNavController(R.id.fragmentContainerView).navigate(R.id.fragmentVehicalPhotos)
            }
        })

        vm.vehicleInformationResponse.observe(this, Observer { event->
            event.getContentIfNotHandled()?.let { response->
                when(response){
                    is Resource.Success->{
                        binding.progressBar.visibility=GONE
                        binding.llScreen.visibility=View.VISIBLE
                        response.data?.let {
                            Utils.logThis(tag, "Vehicle Information Response  is :   ${it.Result}")
                            Utils.setImage(activity,it.Result.DisplayImage,binding.ivVehiclePhoto)
                            binding.tvUserName.text = "Yamaha "+it.Result.Model+" - "+it.Result.VIN
                            binding.tvColor.text = it.Result.Colour
                            binding.tvYear.text = it.Result.Year
                            binding.tvModel.text = it.Result.Model
                        }
                    } is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
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
    }

    override fun onBackPressed() {
        finish()
    }

}