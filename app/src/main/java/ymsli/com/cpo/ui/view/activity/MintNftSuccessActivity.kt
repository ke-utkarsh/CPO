package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_mint_nft.*
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.LineResult
import ymsli.com.cpo.databinding.ActivityMintNftBinding
import ymsli.com.cpo.ui.viewModel.MintNftSuccessViewModel
import ymsli.com.cpo.ui.viewModel.ModifyItemsViewModel
import ymsli.com.cpo.ui.viewModel.ServiceItemsViewModel
import ymsli.com.cpo.utils.CancelableToast
import ymsli.com.cpo.utils.Constants
import ymsli.com.cpo.utils.Resource

@AndroidEntryPoint
class MintNftSuccessActivity: AppCompatActivity() {
    private lateinit var mBinding : ActivityMintNftBinding
    private var activity : Activity = this@MintNftSuccessActivity
    private val vmDetail: MintNftSuccessViewModel by viewModels()
    lateinit var message : String
    private var hash: String = ""
    private var hashUrl: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMintNftBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.hide()
        clickListeners()
        responseObservers()
        message = intent.extras?.getString(Constants.MESSAGE).toString()
        if(message.contains("service",true)){
            mBinding.tvMsg.text=message
            vmDetail.maintenanceDetailApi(RequestBodies.GetMaintenanceRequestBody(intent.getStringExtra(Constants.MAINTENANCE_ID).toString()))
        }else{
            hash = intent.extras?.getString(Constants.TX_HASH).toString()
            hashUrl = intent.extras?.getString(Constants.TX_URL).toString()
        }
        if(message.contains("Offer",true)){
            mBinding.tvMsg.text="NFT transferred successfully"
        }


        //mBinding.tvMsg.text = message
        if(hash!="null"){
            mBinding.tvTxnHash.text = hash
        }

    }

    private fun responseObservers() {
        vmDetail.maintenanceDetail.observe(this, Observer {
                event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.pbProgress.visibility = View.GONE
                        response.data?.let {
                            Log.e("Maintenance Detail Api Response: ",it.Result.toString())
                            hash=it.Result.TxHash.toString()
                            hashUrl=it.Result.TxHashUrl.toString()
                            mBinding.tvMsg.text = message
                            mBinding.tvTxnHash.text = hash
                        }
                    }
                    is Resource.Error -> {
                        mBinding.pbProgress.visibility = View.GONE
                        response.message?.let { message ->
                            //Utils.logThis(tag, "Error message is : $message")
                            if (message == "Unauthorized") {
                                CancelableToast(activity, resources.getString(R.string.unauthorized_error)).alert()
                            } else {
                                CancelableToast(activity,message).error()
                            }
                        }
                    }
                    is Resource.Loading -> {
                        mBinding.pbProgress.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun clickListeners() {
        mBinding.btnBack.setOnClickListener{
            if(message.contains("Offer",true)){
                var intent=Intent(activity,CustomerNftDetailActivity::class.java)
                startActivity(intent)
            }
            finish()
        }
        mBinding.tvTxnHash.setOnClickListener {
            var intent=Intent(applicationContext,WebViewActivity::class.java)
            intent.putExtra(Constants.WEBVIEW_TITLE,"Tx Hash")
            intent.putExtra(Constants.URL,hashUrl)
            startActivity(intent)
        }
    }


}