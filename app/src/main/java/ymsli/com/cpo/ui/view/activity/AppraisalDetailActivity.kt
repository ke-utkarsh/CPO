package ymsli.com.cpo.ui.view.activity

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import com.github.anastr.speedviewlib.components.Style
import dagger.hilt.android.AndroidEntryPoint
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.AppraisalDetailModel
import ymsli.com.cpo.databinding.ActivityAppraisalDetailBinding
import ymsli.com.cpo.ui.view.adapter.AppraisalGradeListAdapter
import ymsli.com.cpo.utils.Constants


@AndroidEntryPoint
open class AppraisalDetailActivity : AppCompatActivity() {
    private var activity: Activity =this@AppraisalDetailActivity
    lateinit var binding: ActivityAppraisalDetailBinding
    private lateinit var appraisalHistoryDetail : AppraisalDetailModel
    private var tabType="Summary"
    private lateinit var displayImage: String

    private val PERMISSIONREQUESTCODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAppraisalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        initView()
        onClickListener()
    }



    private fun initView() {
        appraisalHistoryDetail = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(
                Constants.APPRAISAL_DETAIL,
                AppraisalDetailModel::class.java
            )!!
        else
            intent.getSerializableExtra(Constants.APPRAISAL_DETAIL)!! as AppraisalDetailModel

        displayImage= intent.getStringExtra(Constants.VEHICLE_IMAGE).toString()

        binding.item=appraisalHistoryDetail
        binding.executePendingBindings()
        initSpeedoMeter()
        loadImage()
        loadBelowImageData()
        updateAppraisalGradeList()
        handleTabClick()

    }

    private fun initSpeedoMeter() {
        binding.svlSpeed.centerCircleColor=Color.TRANSPARENT
        binding.svlSpeed.makeSections(3,Color.CYAN,Style.BUTT)
        binding.svlSpeed.sections[0].color=Color.parseColor("#D21404")
        binding.svlSpeed.sections[1].color=Color.parseColor("#E6CC00")
        binding.svlSpeed.sections[2].color=Color.parseColor("#1c70c8")
        binding.svlSpeed.speedTo(appraisalHistoryDetail.Point.toFloat())
        binding.svlSpeed.withTremble=false
    }

    private fun onClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.clSummary.setOnClickListener{
            tabType = "Summary"
            handleTabClick()

        }
        binding.clEvaluation.setOnClickListener{
            tabType = "Evaluation"
            handleTabClick()
        }

        binding.ivDownload.setOnClickListener {
            if (checkPermission()) {
                //Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                requestPermission();
            }
            if(checkPermission()){
                var browser: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(appraisalHistoryDetail.AppraisalSummaryUrl))
                startActivity(browser)
//                var manager: DownloadManager =
//                    getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager;
//                var uri = Uri.parse(appraisalHistoryDetail.AppraisalSummaryUrl)
//                var request=DownloadManager.Request(uri)
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
//                val reference: Any = manager.enqueue(request)
//                if(reference!=0L && reference!=null){
//
//                }
            }
        }
    }

    private fun checkPermission(): Boolean {
        // checking of permissions.
        val permission1 = ContextCompat.checkSelfPermission(applicationContext, WRITE_EXTERNAL_STORAGE)
        val permission2 = ContextCompat.checkSelfPermission(applicationContext, READ_EXTERNAL_STORAGE)
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE), PERMISSIONREQUESTCODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == PERMISSIONREQUESTCODE) {
            if (grantResults.size > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                val writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (writeStorage && readStorage) {
                    //Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }



    private fun handleTabClick() {
        if(tabType=="Summary")
        {
            binding.svSummary.visibility=View.VISIBLE
            binding.svEvaluation.visibility=View.GONE
            binding.clEvaluation.setBackgroundColor(Color.TRANSPARENT)
            binding.clSummary.setBackgroundResource(R.drawable.create_nft_button_background)
            binding.tvSummary.setTextColor(Color.WHITE)
            binding.tvEvaluation.setTextColor(Color.parseColor("#141414"))


        }
        else
        {
            binding.svEvaluation.visibility=View.VISIBLE
            binding.svSummary.visibility=View.GONE
            binding.clSummary.setBackgroundColor(Color.TRANSPARENT)
            binding.clEvaluation.setBackgroundResource(R.drawable.create_nft_button_background)
            binding.tvEvaluation.setTextColor(Color.WHITE)
            binding.tvSummary.setTextColor(Color.parseColor("#141414"))

        }
    }
    private fun updateAppraisalGradeList() {
        val adapter = appraisalHistoryDetail.let {
            AppraisalGradeListAdapter(activity,
                it.AppraisalGradeList)
        }
        binding.rvAppraisalGradeList.adapter=adapter
    }

    private fun loadBelowImageData(){
        if(appraisalHistoryDetail.ApplicableOfKeyNumberPlate)binding.tvApplicableOfKeyNumberPlate.text="Yes"
        else binding.tvApplicableOfKeyNumberPlate.text="No"

        if(appraisalHistoryDetail.OwnereshipOfKeyLicensePlates)binding.tvOwnereshipOfKeyLicensePlates.text="Yes"
        else binding.tvOwnereshipOfKeyLicensePlates.text="No"

        if(appraisalHistoryDetail.OwnerCertification)binding.tvOwnerCertification.text="Yes"
        else binding.tvOwnerCertification.text="No"

        if(appraisalHistoryDetail.OwnerManual)binding.tvOwnerManual.text="Yes"
        else binding.tvOwnerManual.text="No"

        if(appraisalHistoryDetail.MaintenanceBook)binding.tvMaintenanceBook.text="Yes"
        else binding.tvMaintenanceBook.text="No"
    }
    private fun loadImage() {
        Glide.with(activity)
            .load(displayImage)
            .apply(RequestOptions.centerInsideTransform())
            .placeholder(R.drawable.ic_image_black_24dp)
            .error(R.drawable.error_photo)
            .signature(
                ObjectKey(
                    displayImage + System.currentTimeMillis().toString()
                )
            ).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.pbProgressBar.visibility = View.GONE
                    return false;
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.pbProgressBar.visibility = View.GONE
                    return false;
                }
            })
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    @Nullable transition: Transition<in Drawable?>?
                ) {
                    binding.ivVehicleImage.setBackgroundDrawable(resource)
                }
            })
    }
}