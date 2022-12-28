package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.walletconnect.Session
import org.walletconnect.nullOnThrow
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.OfferList
import ymsli.com.cpo.databinding.ActivityOfferListBinding
import ymsli.com.cpo.ui.view.adapter.OfferListAdapter
import ymsli.com.cpo.ui.viewModel.OfferListViewModel
import ymsli.com.cpo.utils.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class OfferListActivity : AppCompatActivity(), Session.Callback {
    private lateinit var binding: ActivityOfferListBinding
    private var activity = this@OfferListActivity
    private lateinit var offerListData: ArrayList<OfferList>
    private val vm: OfferListViewModel by viewModels()
    private lateinit var vehicleId: String
    private lateinit var dialog:Dialog
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private val TAG="Offer List Screen"
    private var txRequest: Long? = null
    private var isConnected=false
    private var _position: Int =0
    private lateinit var dialog1: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfferListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        initView()
        responseObserver()
        clickListener()

    }

    override fun onStart() {
        super.onStart()
        initialSetup()
    }

    override fun onStatus(status: Session.Status) {
        when(status) {
            Session.Status.Approved -> sessionApproved()
            Session.Status.Closed -> sessionClosed()
            Session.Status.Connected,
            Session.Status.Disconnected ,
            is Session.Status.Error -> {
                // Do Stuff
                Log.i(TAG, "onStatus: Session.Status.Error${status}")
                Log.i(TAG, "onStatus: ExampleApplication.session:${MyApplication.session}")
            }
        }
    }

    override fun onMethodCall(call: Session.MethodCall) {
        Log.i(TAG, "onMethodCall: call:${call}")
    }
    private fun sessionApproved() = with(binding) {
        uiScope.launch {
            if(!isConnected){
                isConnected=true
                showWalletDialog("To accept the offer please confirm below","Proceed",_position)
            }
        }
    }

    private fun sessionClosed()  = with(binding){
        uiScope.launch {
            isConnected=false
        }
    }

    private fun clickListener() {
        binding.ivBack.setOnClickListener {
            try{
                MyApplication.session.kill()
            }catch (e: Exception){
                Log.i("Profile Destroy: ",e.toString() )
            }
            isConnected=false
            finish()
        }
    }



    private fun initView() {
        binding.tvPageHead.text=intent.getStringExtra(Constants.HEADER).toString()
        vehicleId=intent.getStringExtra(Constants.VEHICLE_ID).toString()
        vm.offersRequest(RequestBodies.VehicleInformationBody(intent.getStringExtra(Constants.VEHICLE_ID).toString()))
    }
    private fun responseObserver() {
        vm.offerListResponse.observe(this, Observer {
                event->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        response.data?.let { responseData ->
                            Utils.logThis("Offer Lists are :",responseData.Result.toString())
                            offerListData=responseData.Result as ArrayList<OfferList>
                            if(offerListData.size==0){
                                binding.tvNoData.visibility=View.VISIBLE
                                binding.rvOfferItems.visibility=View.GONE
                            }else{
                                val adapter=OfferListAdapter(activity,offerListData,{
                                    showRejectDialog(vehicleId,offerListData[it].OfferId)
                                },{
                                    _position=it
                                    if(isConnected){
                                        showWalletDialog("To accept the offer please confirm below","Proceed",it)
                                    }else{
                                        showWalletDialog("Please connect your wallet with CPO","Connect",it)
                                    }

                                })
                                binding.rvOfferItems.adapter=adapter
                            }

                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        response.message?.let { responseData ->
                            val message = responseData
                            CancelableToast(activity, message).error()
                        }
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
        vm.rejectOfferResponse.observe(this, Observer { event->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        response.data?.let { responseData ->
                            //Utils.logThis("Reject :",responseData.Result.toString())
                            vm.offersRequest(RequestBodies.VehicleInformationBody(vehicleId))
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        response.message?.let { responseData ->
                            val message = responseData
                            CancelableToast(activity, message).error()
                        }
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
        vm.acceptOfferResponse.observe(this, Observer { event->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        response.data?.let { responseData ->
                            var intent=Intent(activity,MintNftSuccessActivity::class.java)
                            intent.putExtra(Constants.MESSAGE, "Offer Accepted Successfully")
                            intent.putExtra(Constants.TX_HASH, responseData.Result.TxHash.toString())
                            intent.putExtra(Constants.TX_URL,responseData.Result.TxHashUrl.toString())
                            startActivity(intent)
                            finish()
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        response.message?.let { responseData ->
                            val message = responseData
                            CancelableToast(activity, message).error()
                        }
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun showWalletDialog(text:String, button: String, position: Int){
        dialog1= Dialog(activity)
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog1.setContentView(R.layout.dialog_connect)
        dialog1.findViewById<TextView>(R.id.tvPlease).text=text
        dialog1.findViewById<Button>(R.id.btnConnect).text=button
        if(isConnected){
            dialog1.findViewById<Button>(R.id.btnConnect).setBackgroundColor(Color.parseColor("#22AE42"))
        }
        dialog1.findViewById<ImageView>(R.id.ivClose1).setOnClickListener { dialog1.dismiss() }
        dialog1.findViewById<Button>(R.id.btnConnect).setOnClickListener {
            dialog1.dismiss()
            if(isConnected){
                val params=offerListData[position].SendTranscationParams
                val txRequest = System.currentTimeMillis()
                try{
                    MyApplication.session.performMethodCall(
                        Session.MethodCall.SendTransaction(txRequest,params.OwnerWallet,params.SmartContractAddress,
                            null,"0x2540be400","0x7a120", "0x00",params.EncodedData),
                        ::handleResponse
                    )
                    this@OfferListActivity.txRequest = txRequest
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse("wc:")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }catch(e:Exception){
                    Log.i(TAG, "SendTransaction Exception: :${e.message}")
                }
            }else{
                MyApplication.resetSession()
                MyApplication.session.addCallback(this@OfferListActivity)
                val i = Intent(Intent.ACTION_VIEW)
                Log.i(
                    TAG,
                    "onStart: ExampleApplication.config.toWCUri():${MyApplication.config.toWCUri()}"
                )
                i.data = Uri.parse(MyApplication.config.toWCUri())
                startActivity(i)
            }

        }
        dialog1.show()

    }

    private fun initialSetup() {
        val session = nullOnThrow { MyApplication.session } ?: return
        session.addCallback(this)
        sessionApproved()
    }

    private fun handleResponse(resp: Session.MethodCall.Response)  = with(binding){
        dialog1.dismiss()
        Log.i(TAG, "handleResponse: response:${resp}")
        if(resp.error==null){
            Log.i("TransferTxHash: ",offerListData[_position].VehicleId +" "+ offerListData[_position].OfferId+" "+resp.result)
        }

        if (resp.id == txRequest) {
            txRequest = null
            uiScope.launch {
                if(resp.result!=null){
                    Log.i("Calling Offer Accepted Api: ","True")
                    vm.acceptRequest(RequestBodies.OfferAcceptedRequest(offerListData[_position].VehicleId,offerListData[_position].OfferId,resp.result.toString()))
                }else{
                    CancelableToast(activity,resp.error.toString()).alert()
                }
            }
        }
    }

    override fun onDestroy() {
        try{
            MyApplication.session.removeCallback(this)
        }catch (e: Exception){
            Log.i("Profile Destroy: ",e.toString() )
        }
        super.onDestroy()
    }


    private fun showRejectDialog(vehicleId:String, offerId:String) {
        dialog= Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_reject);
        val yesBtn=dialog.findViewById(R.id.tvSubmit) as TextView
        val noBtn=dialog.findViewById(R.id.tvCancel) as TextView
        yesBtn.setOnClickListener {
            vm.rejectOffer(RequestBodies.OfferRejectRequest(vehicleId,offerId))
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }
}