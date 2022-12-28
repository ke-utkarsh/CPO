package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.list_item_service_item.view.*
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.ServiceDataResult
import ymsli.com.cpo.data.model.VehicleInformationData
import ymsli.com.cpo.databinding.ActivityServiceItemsBinding
import ymsli.com.cpo.ui.view.adapter.ServiceItemAdapter
import ymsli.com.cpo.ui.viewModel.GetNewUidViewModel
import ymsli.com.cpo.ui.viewModel.ServiceItemsViewModel
import ymsli.com.cpo.utils.CancelableToast
import ymsli.com.cpo.utils.Constants
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.Utils
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class ServiceItemsActivity : AppCompatActivity() {
    private var mServiceItemList: ArrayList<ServiceDataResult> = ArrayList()
    private val checkedList: ArrayList<ServiceDataResult> = ArrayList()
    private lateinit var mBinding: ActivityServiceItemsBinding
    private val vm: ServiceItemsViewModel by viewModels()
    public lateinit var activity: Activity
    private val tag = "ServiceItem"
    private lateinit var vehicleInformationData: VehicleInformationData
    lateinit var appointmentId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityServiceItemsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.hide()
        activity= this@ServiceItemsActivity
        initView()
        responseObserver()
        onClickListener()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter("finish_activity"))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    private fun onClickListener() {

        mBinding.ivBack.setOnClickListener {
            finish()
        }

        mBinding.btnNext.setOnClickListener {
            checkedList.clear()
            for (i in mServiceItemList.indices) {
                if (mBinding.rvServiceItem[i].cbCheck.isChecked)
                {
                    val uniqueID: String = UUID.randomUUID().toString()
                    checkedList.add(ServiceDataResult(
                            mServiceItemList[i].Description,
                            mServiceItemList[i].Id,
                            mServiceItemList[i].Name,
                            uniqueID,
                        )
                    )
                }
            }

            if (checkedList.size == 0) {
                Toasty.warning(activity, "Please select at least one item").show()
            } else {
                val intent = Intent(applicationContext, ModifyItemsActivity::class.java)
                intent.putParcelableArrayListExtra(Constants.CHECKED_SERVICE_ITEM, checkedList)
                intent.putExtra(Constants.VEHICLE_INFORMATION, vehicleInformationData)
                intent.putExtra(Constants.APPOINTMENT_ID, appointmentId)
                intent.putExtra(Constants.PREVIOUS_ACTIVITY, activity.localClassName)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun initView() {
        appointmentId = intent.getStringExtra(Constants.APPOINTMENT_ID).toString()
        vehicleInformationData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(
                Constants.VEHICLE_INFORMATION,
                VehicleInformationData::class.java
            )!!
        else
            intent.getSerializableExtra(Constants.VEHICLE_INFORMATION) as VehicleInformationData
        vm.serviceItemRequest()
        mBinding.rvServiceItem.layoutManager = LinearLayoutManager(this)

    }

    private fun responseObserver() {
        vm.serviceItemResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = View.GONE
                        response.data?.let {
                            Utils.logThis(tag, "Active warranties Response  is :   ${it.Result}")
                            if (it.Result.isEmpty()) {
                                mBinding.tvNoData.visibility = View.VISIBLE
                                mBinding.rvServiceItem.visibility = View.GONE
                            } else {
                                mBinding.tvNoData.visibility = View.GONE
                                mBinding.rvServiceItem.visibility = View.VISIBLE
                                mServiceItemList = it.Result as ArrayList<ServiceDataResult>
                                val adapter = ServiceItemAdapter(mServiceItemList) { it ->
                                    if (mBinding.rvServiceItem[it].cbCheck.isChecked) {
                                        mBinding.rvServiceItem[it].cvJobCard.setBackgroundResource(R.drawable.black_line_white_background)
                                        mBinding.rvServiceItem[it].cbCheck.buttonTintList =
                                            ColorStateList(
                                                arrayOf(intArrayOf(-android.R.attr.checked)),
                                                intArrayOf(Color.BLACK)
                                            )

                                    } else {
                                        mBinding.rvServiceItem[it].cbCheck.buttonTintList =
                                            ColorStateList(
                                                arrayOf(intArrayOf(-android.R.attr.checked)),
                                                intArrayOf(Color.GRAY)
                                            )
                                        mBinding.rvServiceItem[it].cvJobCard.setBackgroundResource(R.drawable.line_white_background)
                                    }
                                }
                                mBinding.rvServiceItem.adapter = adapter
                            }
                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = View.GONE
                        response.message?.let { message ->
                            Utils.logThis(tag, "Error message is : $message")
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
                        mBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })

    }

    private val broadcastReceiver: BroadcastReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            var action= intent?.action
            if(action.equals("finish_activity")){
                finish()
            }
        }

    }

}