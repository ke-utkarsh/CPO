package ymsli.com.cpo.ui.view.fragment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_vehicle_information_customer.*
import ymsli.com.cpo.R
import ymsli.com.cpo.databinding.FragmentVehicleInformationCustomerBinding
import ymsli.com.cpo.ui.view.activity.MintNftSuccessActivity
import ymsli.com.cpo.ui.view.activity.WebViewActivity
import ymsli.com.cpo.ui.viewModel.VehicleInformationViewModel
import ymsli.com.cpo.utils.Constants

import ymsli.com.cpo.utils.Resource


class FragmentVehicleInformationCustomer : Fragment() {
    private lateinit var mBinding: FragmentVehicleInformationCustomerBinding
    private val vm: VehicleInformationViewModel by activityViewModels()
    private lateinit var txHashUrl: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentVehicleInformationCustomerBinding.inflate(inflater, container, false)
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        responseObserver()
        clickObserver()
    }

    private fun clickObserver() {
        mBinding.tvTxHash.setOnClickListener {
            var intent=Intent(activity,WebViewActivity::class.java)
            intent.putExtra(Constants.WEBVIEW_TITLE,"Tx Hash")
            intent.putExtra(Constants.URL,txHashUrl)
            startActivity(intent)
        }
    }


    private fun responseObserver() {
        vm.vehicleInformationResponse.observe(viewLifecycleOwner, Observer { event ->
            event.peekContent().let { response->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            mBinding.item=it.Result
                            mBinding.executePendingBindings()
                            if(it.Result.TxHash==""){
                                mBinding.llTxHash.visibility=View.GONE
                                mBinding.vwTxHash.visibility=View.GONE
                            }else{
                                txHashUrl=it.Result.TxHashUrl.toString()
                            }
                        }
                    }
                    else -> {}
                }
            }
        })
    }
}