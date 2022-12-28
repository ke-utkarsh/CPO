package ymsli.com.cpo.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import ymsli.com.cpo.R
import ymsli.com.cpo.databinding.FragmentVehicalInformationBinding
import ymsli.com.cpo.ui.viewModel.VehicleInformationViewModel
import ymsli.com.cpo.utils.Resource

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentVehicleInformationDealer.newInstance] factory method to
 * create an instance of this fragment.
 */

class FragmentVehicleInformationDealer : Fragment() {
    private lateinit var mBinding: FragmentVehicalInformationBinding
    private val vm: VehicleInformationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentVehicalInformationBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        responseObserver()
        setOnClickListener()
    }

    private fun setOnClickListener() {
       mBinding.btnNext.setOnClickListener {
           vm.nextEventClick.value = true
       }
    }

    private fun responseObserver() {
        vm.vehicleInformationResponse.observe(viewLifecycleOwner, Observer { event ->
            event.peekContent().let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {

                            mBinding.tvVin.text = it.Result.VIN
                            mBinding.tvEngineNumber.text = it.Result.EngineNo
                            mBinding.tvChassisNumber.text = it.Result.ChassisNo
                            mBinding.tvDisplacement.text = it.Result.Displacement
                            mBinding.tvTopSpeed.text = it.Result.TopSpeed
                            mBinding.tvAccelaration.text = it.Result._0_to_100
                            mBinding.tvOwnerName.text= it.Result.CustomerName.toString()
                        }
                    }
                    else -> {}
                }
            }
        })
    }

}