package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.*
import ymsli.com.cpo.databinding.ActivityWarrantiesBinding
import ymsli.com.cpo.ui.view.adapter.WarrantiesAdapter
import ymsli.com.cpo.ui.viewModel.ActiveWarrantiesViewModel
import ymsli.com.cpo.utils.CancelableToast
import ymsli.com.cpo.utils.Constants
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.Utils

@AndroidEntryPoint
class WarrantiesActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityWarrantiesBinding
    private var activity : Activity = this@WarrantiesActivity
    private val tag = "Active Warranties"
    private val vm: ActiveWarrantiesViewModel by viewModels()
    private lateinit var activeWarrantiesList : ArrayList<ActiveWarrantiesData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=ActivityWarrantiesBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        initView()
        responseObserver()
        clickListener()
    }


    private fun initView() {
        mBinding.rvWarranties.layoutManager=LinearLayoutManager(this)
        vm.activeWarrantiesRequest()
    }


    private fun clickListener() {
        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun responseObserver() {
        vm.activeWarrantiesResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                         mBinding.progressBar.visibility = GONE
                        response.data?.let {
                            Utils.logThis(tag, "Active warranties Response  is :   ${it.Result}")
                            if(it.Result.isEmpty())
                            {
                                mBinding.tvNoData.visibility = VISIBLE
                                mBinding.rvWarranties.visibility = GONE
                            }
                            else{
                                mBinding.tvNoData.visibility = GONE
                                mBinding.rvWarranties.visibility = VISIBLE
                                activeWarrantiesList = it.Result as ArrayList<ActiveWarrantiesData>
                                val adapter=WarrantiesAdapter(activity,activeWarrantiesList) { _position ->
                                    val intent = Intent(applicationContext, VehiclesInformationDealer::class.java)
                                    intent.putExtra(Constants.VEHICLE_ID, activeWarrantiesList[_position].VechileId)
                                    startActivity(intent)
                                }
                                mBinding.rvWarranties.adapter=adapter
                            }

                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = View.GONE
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
                        mBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

}