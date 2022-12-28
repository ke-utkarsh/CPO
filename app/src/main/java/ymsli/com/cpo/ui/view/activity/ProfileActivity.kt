package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.walletconnect.Session
import org.walletconnect.nullOnThrow
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.databinding.ActivityProfileBinding
import ymsli.com.cpo.databinding.DialogLinkCryptoBinding
import ymsli.com.cpo.ui.viewModel.ProfileViewModel
import ymsli.com.cpo.utils.*

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity(), Session.Callback {
    private var activity: Activity = this@ProfileActivity
    private lateinit var dialogBinding: DialogLinkCryptoBinding
    private lateinit var mBinding: ActivityProfileBinding
    private val vm: ProfileViewModel by viewModels()
    private val tag = "Profile Items"
    private lateinit var linkCryptoDialog: Dialog
    private lateinit var walletUrl:String
    private lateinit var txUrl:String
    private val uiScope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.hide()
        init()
        observeData()
        onClickListener()
    }

    private fun init() {

        vm.profileRequest()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(applicationContext, CustomerNftDetailActivity::class.java)
        startActivity(intent)
    }

    private fun observeData() {
        vm.profileResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = GONE
                        response.data?.let {
                            Utils.logThis(tag, "Profile Response  is :   ${it.Result}")
                            mBinding.item = it.Result
                            walletUrl=it.Result.WalletAddressUrl
                            txUrl=it.Result.TxHashUrl
                            if (it.Result.WalletAddress.isNotEmpty()) {
                                mBinding.cryptoWallet.visibility = View.GONE
                                mBinding.llWalletAddress.visibility = View.VISIBLE
                                mBinding.llTransactionHash.visibility = View.VISIBLE
                            } else {
                                mBinding.cryptoWallet.visibility = View.VISIBLE
                                mBinding.llWalletAddress.visibility = View.GONE
                                mBinding.llTransactionHash.visibility = View.GONE
                            }
                            mBinding.executePendingBindings()
                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = GONE
                        response.message?.let { message ->
                            if (message == "Unauthorized") {
                                CancelableToast(activity, resources.getString(R.string.unauthorized_error)).alert()
                                Utils.sessionTimeOutStartLoginScreen(activity)
                            } else {
                                CancelableToast(activity,message).error()
                            }
                        }
                    }
                    is Resource.Loading -> {
                        mBinding.progressBar.visibility = VISIBLE
                    }

                }
            }
        })


        vm.walletlinkResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = GONE
                        response.data?.let {
                            linkCryptoDialog.dismiss()
                            Utils.logThis(tag, "Profile Response  is :   ${it.Result}")
                            vm.profileRequest()

                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = GONE
                        response.message?.let { message ->
                            Utils.logThis(tag, "Error message is : $message")
                            if (message == "Unauthorized") {
                                CancelableToast(activity, resources.getString(R.string.unauthorized_error)).alert()
                                Utils.sessionTimeOutStartLoginScreen(activity)
                            } else {
                                if(!message.contains("Already",true)){
                                    CancelableToast(activity,message).error()
                                }

                            }
                        }
                    }
                    is Resource.Loading -> {
                        mBinding.progressBar.visibility = VISIBLE
                    }

                }
            }
        })


    }

    private fun onClickListener() {

        mBinding.ivBack.setOnClickListener {
            val intent = Intent(applicationContext, CustomerNftDetailActivity::class.java)
            startActivity(intent)
        }

        mBinding.cryptoWallet.setOnClickListener {
            showLinkCryptoDialog()
        }

        mBinding.tvUserName.setOnClickListener {
            showActiveWarranties()
        }

        mBinding.tvCryptoWalletAddress.setOnClickListener{
            val intent = Intent(activity,WebViewActivity::class.java)
            intent.putExtra(Constants.WEBVIEW_TITLE,"Wallet Address")
            intent.putExtra(Constants.URL,walletUrl)
            startActivity(intent)
        }

        mBinding.tvTransactionHash.setOnClickListener{
            val intent = Intent(activity,WebViewActivity::class.java)
            intent.putExtra(Constants.WEBVIEW_TITLE,"Transaction Hash")
            intent.putExtra(Constants.URL,txUrl)
            startActivity(intent)
        }
    }


    private fun showActiveWarranties() {
        val intent = Intent(this, WarrantiesActivity::class.java)
        startActivity(intent);
    }


    /**
     * This function is used to show error Dialog
     */
    private fun showLinkCryptoDialog() {
        initialSetup()
        try {
            dialogBinding = DialogLinkCryptoBinding.inflate(activity.layoutInflater)
            linkCryptoDialog = Dialog(activity, R.style.DialogStyle)
            linkCryptoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            linkCryptoDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            linkCryptoDialog.setCancelable(true)
            linkCryptoDialog.setContentView(dialogBinding.getRoot())
            linkCryptoDialog.show()

            dialogBinding.tvWalletConnect.setOnClickListener {
                MyApplication.resetSession()
                linkCryptoDialog.dismiss()
                MyApplication.session.addCallback(this@ProfileActivity)
                val i = Intent(Intent.ACTION_VIEW)
                Log.i("WalletConnectDialog", "onStart: ExampleApplication.config.toWCUri():${MyApplication.config.toWCUri()}")
                i.data = Uri.parse(MyApplication.config.toWCUri())
                startActivity(i)
            }
            dialogBinding.btnLinkWallet.setOnClickListener {
                if (!dialogBinding.etWalletaddress.text.toString().equals("")) {
                    linkCryptoDialog.dismiss()
                    val walletLinkRequestBody = RequestBodies.WalletLinkRequestBody(dialogBinding.etWalletaddress.text.toString())
                    vm.walletLinkRequestCall(walletLinkRequestBody)
                } else {
                    Toasty.info(activity, "Please enter the wallet address", Toast.LENGTH_LONG).show()
                }
            }
        } catch (ex: Exception) {
            Log.e(tag, ex.localizedMessage!!)
        }


    }

    override fun onMethodCall(call: Session.MethodCall) {
        Log.i("WalletConnectMC", "onMethodCall: call:${call}")
    }

    override fun onStatus(status: Session.Status) {
        when(status) {
            Session.Status.Approved -> sessionApproved()
            Session.Status.Closed -> sessionClosed()
            Session.Status.Connected,
            Session.Status.Disconnected ,
            is Session.Status.Error -> {
                // Do Stuff
                Log.i("WalletConnectOS", "onStatus: Session.Status.Error${status}")
                Log.i("WalletConnectOS", "onStatus: ExampleApplication.session:${MyApplication.session}")
            }
        }
    }
    private fun sessionApproved() = with(dialogBinding) {
        uiScope.launch {
            if(MyApplication.session.approvedAccounts()?.first()!=null){
                dialogBinding.etWalletaddress.setText(MyApplication.session.approvedAccounts()?.first().toString())
                //Toast.makeText(activity,"Linking wallet.. please wait",Toast.LENGTH_SHORT).show()
                vm.walletLinkRequestCall(RequestBodies.WalletLinkRequestBody(MyApplication.session.approvedAccounts()?.first().toString()))
                linkCryptoDialog.dismiss()
            }else{

            }


        }
    }

    private fun sessionClosed()  = with(mBinding){
//        uiScope.launch {
//            screenMainStatus.text = "Disconnected"
//            screenMainConnectButton.visibility = View.VISIBLE
//            screenMainDisconnectButton.visibility = View.GONE
//            screenMainResponse.visibility = View.GONE
//
//        }
    }

    override fun onStart() = with(mBinding) {
        super.onStart()
        initialSetup()
    }

    private fun initialSetup() {
        val session = nullOnThrow { MyApplication.session } ?: return
        session.addCallback(this)
        sessionApproved()
    }

    override fun onDestroy() {
        try{
            MyApplication.session.removeCallback(this)
        }catch (e: Exception){
            Log.i("Profile Destroy: ",e.toString() )
        }

        super.onDestroy()
    }


}