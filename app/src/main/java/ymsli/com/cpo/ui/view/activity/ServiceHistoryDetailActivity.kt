package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
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
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.MaintenanceConcerned
import ymsli.com.cpo.data.model.MaintenanceList
import ymsli.com.cpo.data.model.VehicleInformationData
import ymsli.com.cpo.databinding.ActivityAppraisalDetailBinding
import ymsli.com.cpo.databinding.ActivityServiceHistoryDetailBinding
import ymsli.com.cpo.ui.view.adapter.ServiceHistoryParentAdapter
import ymsli.com.cpo.ui.view.adapter.ServiceSubmitParentAdapter
import ymsli.com.cpo.utils.Constants
import ymsli.com.cpo.utils.cameraUtils.CameraClickActivity

@AndroidEntryPoint
class ServiceHistoryDetailActivity: AppCompatActivity()  {
    private var activity: Activity =this@ServiceHistoryDetailActivity
    lateinit var binding: ActivityServiceHistoryDetailBinding
    private lateinit var maintenanceData: MaintenanceList
    private lateinit var vin: String
    private lateinit var serviceHistoryParentAdapter : ServiceHistoryParentAdapter
    private var commonSelectedPosition:Int=0
    private lateinit var afterImages: List<MaintenanceConcerned>
    private lateinit var beforeImages: List<MaintenanceConcerned>
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityServiceHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        initView()
        onClickListener()
        setupImages()
    }



    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.tvTxHash.setOnClickListener {
            var intent=Intent(activity,WebViewActivity::class.java)
            intent.putExtra(Constants.WEBVIEW_TITLE,"Tx Hash")
            intent.putExtra(Constants.URL,maintenanceData.TxHashUrl.toString())
            startActivity(intent)
        }
        binding.tvMetadata.setOnClickListener {
            var intent=Intent(activity,WebViewActivity::class.java)
            intent.putExtra(Constants.WEBVIEW_TITLE,"Service Metadata")
            intent.putExtra(Constants.URL,maintenanceData.ServiceMetadataUrl.toString())
            startActivity(intent)
        }
    }

    private fun initView() {
        maintenanceData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(
                "Maintenance",
                MaintenanceList::class.java
            )!!
        else
            intent.getSerializableExtra("Maintenance") as MaintenanceList

        vin=intent.getStringExtra("Vin").toString()
        binding.tvJobVin.text= "VIN- $vin"
        binding.item=maintenanceData
        afterImages = maintenanceData.Concerend
        beforeImages = maintenanceData.MaintenanceConcerned
        binding.executePendingBindings()
    }

    private fun setupImages() {

        serviceHistoryParentAdapter = ServiceHistoryParentAdapter(activity,afterImages,beforeImages)

        binding.rvServiceItems.adapter=serviceHistoryParentAdapter

    }

}