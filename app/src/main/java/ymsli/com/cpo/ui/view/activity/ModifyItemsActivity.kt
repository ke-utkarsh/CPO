package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.Observer
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
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.item_list_modify.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.*
import ymsli.com.cpo.databinding.ActivityModifyItemsBinding
import ymsli.com.cpo.ui.view.adapter.ModifyItemsAdapter
import ymsli.com.cpo.ui.viewModel.ModifyItemsViewModel
import ymsli.com.cpo.ui.viewModel.PostImageViewModel
import ymsli.com.cpo.utils.*
import ymsli.com.cpo.utils.Constants.CHECKED_SERVICE_ITEM
import ymsli.com.cpo.utils.cameraUtils.CameraClickActivity
import java.io.File
import java.util.*


@AndroidEntryPoint
class ModifyItemsActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityModifyItemsBinding
    private var activity: Activity = this@ModifyItemsActivity
    private val postImageViewModel: PostImageViewModel by viewModels()
    private val vm:ModifyItemsViewModel by viewModels()
    private lateinit var serviceId: String
    private lateinit var dialog: Dialog
    private lateinit var modifyItemsStringList:ArrayList<ServiceDataResult>
    private  var isOtherItem:Boolean=false
    private  var isOtherItemClicked:Boolean=false
    private lateinit var modifyItemsAdapter:ModifyItemsAdapter
    private lateinit var otherItemsAdapter:ModifyItemsAdapter
    private var modifyItemList= ArrayList<LineResult>()
    private var otherItemList= ArrayList<LineResult>()
    private var commonSelectedPosition:Int=0
    private lateinit var vehicleInformationData: VehicleInformationData
    lateinit var appointmentId :String
    private var maintenanceLines= ArrayList<RequestBodies.LineItemRequest>()
    private var isSave:Boolean= true
    private var isLoading=false
    private var isUploading=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityModifyItemsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.hide()
        initView()
        onClick()
        responseObserver()
        imageCLickObserver()

    }


    private fun initView() {
        var extra:Bundle?=intent.extras
        if(extra!=null){
            if(extra.containsKey(Constants.APPOINTMENT_ID)){
                appointmentId = intent.getStringExtra(Constants.APPOINTMENT_ID).toString()
                mBinding.tvPageHead.text= "Request id: "+appointmentId.split("-")[4].toUpperCase()
            }

            if(extra.containsKey(Constants.VEHICLE_INFORMATION)){
                vehicleInformationData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    intent.getSerializableExtra(Constants.VEHICLE_INFORMATION, VehicleInformationData::class.java)!!
                else
                    intent.getSerializableExtra(Constants.VEHICLE_INFORMATION) as VehicleInformationData
            }

            if(extra.containsKey(Constants.CHECKED_SERVICE_ITEM)){
                modifyItemsStringList= intent.getParcelableArrayListExtra(CHECKED_SERVICE_ITEM)!!

                for(i in modifyItemsStringList){
                    if(i.Description!="Other items"){
                        modifyItemList.add(LineResult(UUID.randomUUID().toString(),"",LineItemResult(i.Id,i.Name.toString(),i.Description.toString()),
                            ArrayList()
                        ))
                    }else{
                        isOtherItem=true
                    }
                }
                setUpModifyImageClickListener()
                if(isOtherItem){
                    otherItemList.add(LineResult(UUID.randomUUID().toString(),"",LineItemResult(700,"OtherItems","Other items"),
                        ArrayList()
                    ))
                    setUpOtherImageClickListener()
                }else{
                    mBinding.llOtherItems.visibility=GONE
                    mBinding.rvOtherItems.visibility = GONE
                    mBinding.addOtherItems.visibility = GONE
                }
                serviceId =  UUID.randomUUID().toString()
            }
            else if(extra.containsKey(Constants.MAINTENANCE_ID)){
                if(extra.containsKey(Constants.MAINTENANCE_ID)){
                    serviceId= extra.getString(Constants.MAINTENANCE_ID).toString()

                    vm.maintenanceDetailApi(RequestBodies.GetMaintenanceRequestBody(intent.getStringExtra(Constants.MAINTENANCE_ID).toString()))
                }
            }
        }
        enableDisableView(mBinding.llRecyclerViews,false);


    }

    fun enableDisableView(view: View, enabled: Boolean) {
        view.isEnabled = enabled
        if (view is ViewGroup) {
            val group = view
            for (idx in 0 until group.childCount) {
                enableDisableView(group.getChildAt(idx), enabled)
            }
        }
    }

    private fun onClick() {
        mBinding.ivBack.setOnClickListener {
            finish()
        }

        mBinding.addOtherItems.setOnClickListener {
            if (otherItemList[otherItemList.size - 1].LineImages.size == 0 && otherItemList[otherItemList.size - 1].LineDesc.isEmpty()) {
                Toasty.warning(activity, "Add brand name and image in last item").show()
            } else {
                isOtherItem=true
                otherItemList.add(LineResult(UUID.randomUUID().toString(),"",LineItemResult(700,"OtherItems","Other items"),
                    ArrayList()
                ))
                Log.e("Other item List2",otherItemList.toString())
                setUpOtherImageClickListener()
            }
        }

        mBinding.btnSave.setOnClickListener {
            if(isLoading){
                Toasty.warning(activity.baseContext,R.string.while_loading).show()
            }
            else{
                isSave=true
                saveOperation()
            }

        }

        mBinding.btnNext.setOnClickListener {
            if(isLoading){
                Toasty.warning(activity.baseContext,R.string.while_loading).show()
            }else{
                isSave=false
                saveOperation()
            }
        }
    }

    private fun saveOperation() {
        for(i in modifyItemList){
            var lineImages= ArrayList<String>()
            for(j in i.LineImages) lineImages.add(j.Id)
            if(lineImages.isEmpty() && !isSave){
                Toasty.warning(activity,"Click atleast one picture in all fields").show()
                return
            }
            maintenanceLines.add(RequestBodies.LineItemRequest(i.LineId,i.LineDesc,lineImages))
        }
        if(isOtherItem){
            for(i in otherItemList){
                var lineImages= ArrayList<String>()
                for(j in i.LineImages) lineImages.add(j.Id)
                if(lineImages.isEmpty() && !isSave){
                    Toasty.warning(activity,"Click atleast one picture in all fields").show()
                    return
                }
                maintenanceLines.add(RequestBodies.LineItemRequest(i.LineId,i.LineDesc,lineImages))
            }
        }

        var saveNextRequest=RequestBodies.SaveMaintenanceRequestBody(serviceId,maintenanceLines)
        vm.saveNxtMaintenanceApi(saveNextRequest)
    }


    private fun responseObserver() {
        vm.maintenanceDetail.observe(this, Observer {
                event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = GONE
                        response.data?.let {
                            Log.e("Maintenance Detail Api Response: ",it.Result.toString())
                            modifyItemList=it.Result.Lines as ArrayList<LineResult>
                            setUpModifyImageClickListener()
                            if(it.Result.Others.isNotEmpty()){
                                mBinding.llOtherItems.visibility=View.VISIBLE
                                isOtherItem=true
                            }else{
                                mBinding.llOtherItems.visibility=View.GONE
                            }
                            if(isOtherItem){
                                otherItemList=it.Result.Others as ArrayList<LineResult>
                                setUpOtherImageClickListener()
                            }
                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = GONE
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
                        mBinding.progressBar.visibility = VISIBLE
                    }
                }
            }
        })
        vm.saveMaintenanceDetail.observe(this, Observer {event->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = GONE
                        response.data?.let {
                            Log.e("Save Maintenance Detail Api Response: ",it.SuccessMessage.toString())
                            Toasty.info(activity, it.SuccessMessage.toString()).show()
                            if(!isSave){
                                val intent = Intent(activity, ServiceSubmitActivity::class.java)
                                intent.putExtra("appointmentId",appointmentId)
                                intent.putExtra("service_request_id",appointmentId)// this is reuqest Id
                                intent.putExtra("maintenance_id",serviceId)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = GONE
                        response.message?.let { message ->
                            //Utils.logThis(tag, "Error message is : $message")
                            if (message == "Unauthorized") {
                                Toasty.error(activity, resources.getString(R.string.unauthorized_error)).show()
                            } else {
                                Toasty.warning(activity,message).show()
                                if(!isSave){
                                    val intent = Intent(activity, ServiceSubmitActivity::class.java)
                                    intent.putExtra("appointmentId",appointmentId)
                                    intent.putExtra("service_request_id",appointmentId)// this is reuqest Id
                                    startActivity(intent)
                                    finish()
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

    private fun setUpModifyImageClickListener() {

        if (this::modifyItemsAdapter.isInitialized) {
            modifyItemsAdapter.notifyDataSetChanged()
        } else {
            modifyItemsAdapter = ModifyItemsAdapter(activity, modifyItemList, false,{
                modifyItemsAdapter.notifyDataSetChanged()
            }, {
                if(!isLoading){
                    commonSelectedPosition = it
                    if (modifyItemList[commonSelectedPosition].LineDesc.isEmpty()) {
                        Toasty.warning(activity, "Please enter issue details").show()
                    } else {
                        mBinding.rvModifyItems[commonSelectedPosition].etIssue1.isEnabled=false
                        mBinding.rvModifyItems[commonSelectedPosition].etIssue1.isFocusable=false
                        val intent = Intent(activity, CameraClickActivity::class.java)
                        modifyImageClickLauncher.launch(intent)

                    }
                }else{
                    Toasty.error(activity,"Please wait while image is being loaded").show()
                }

            }, { childList, parentPosition ->
                if (childList[1] == 0) {
                    modifyItemList[parentPosition].LineImages.removeAt(childList[0])
                    modifyItemsAdapter.notifyDataSetChanged()
                } else {
                    showImageDialog(
                        modifyItemList[parentPosition].LineDesc,
                        modifyItemList[parentPosition].LineImages[childList[0]].OriginalImageUrl,
                        modifyItemList[parentPosition].LineImages[childList[0]].IpfsUrl
                    )
                }
            })
            mBinding.rvModifyItems.adapter = modifyItemsAdapter
        }
    }


    private fun setUpOtherImageClickListener() {
        if (this::otherItemsAdapter.isInitialized) {
            otherItemsAdapter.notifyDataSetChanged()
        } else {
            otherItemsAdapter = ModifyItemsAdapter(activity, otherItemList, true,{
                otherItemList.removeAt(it)
                otherItemsAdapter.notifyDataSetChanged()
            }, {
                if(!isLoading) {
                    commonSelectedPosition = it
                    if (otherItemList[commonSelectedPosition].LineDesc.isEmpty()) {
                        Toasty.warning(activity, "Please enter issue details").show()
                    } else {
                        mBinding.rvOtherItems[commonSelectedPosition].etIssue1.isEnabled=false
                        mBinding.rvOtherItems[commonSelectedPosition].etIssue1.isFocusable=false
                        val intent = Intent(activity, CameraClickActivity::class.java)
                        otherImageClickLauncher.launch(intent)
                    }
                }else{
                    Toasty.error(activity,"Please wait while image is being loaded").show()
                }

            }, { childList, parentPosition ->
                if (childList[1] == 0) {
                    otherItemList[parentPosition].LineImages.removeAt(childList[0])
                    otherItemsAdapter.notifyDataSetChanged()
                } else {
                    showImageDialog(
                        otherItemList[parentPosition].LineDesc,
                        otherItemList[parentPosition].LineImages[childList[0]].OriginalImageUrl,
                        otherItemList[parentPosition].LineImages[childList[0]].IpfsUrl
                    )
                }
            })
            mBinding.rvOtherItems.adapter = otherItemsAdapter
        }
    }



    private var otherImageClickLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                if (result.data != null) {

                    if (hasActiveInternetConnectionForContext(activity)) {
                        val imageUri = result.data!!.getStringExtra("IMAGE_URI").toString()
                        val file = File(Uri.parse(imageUri).path!!)
                        val requestFile: RequestBody =
                            RequestBody.create(MediaType.parse("multipart/form-data"), file)
                        val body = MultipartBody.Part.createFormData(
                            "file",
                            "IMAGE" + "_" + Utils.getTransactionNumber() + ".png",
                            requestFile
                        )

                        val appointmentIdBody: RequestBody =
                            RequestBody.create(MediaType.parse("text/plain"), appointmentId)

                        val maintenanceIdBody: RequestBody =
                            RequestBody.create(MediaType.parse("text/plain"), serviceId)

                        val maintenanceItemIdBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            otherItemList[commonSelectedPosition].LineItem.Id.toString())

                        val maintenanceLineIdBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            otherItemList[commonSelectedPosition].LineId
                        )


                        val descriptionBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            otherItemList[commonSelectedPosition].LineDesc)
                        val vehicleIdBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            vehicleInformationData.VehicleId
                        )

                        isOtherItemClicked = true
                        postImageViewModel.postMaintenanceImageData(appointmentIdBody,
                            maintenanceIdBody,
                            maintenanceItemIdBody,
                            maintenanceLineIdBody,
                            descriptionBody,
                            vehicleIdBody,
                            body
                        )
                    } else {
                        Toasty.warning(activity, getString(R.string.no_internet_connection)).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (result.resultCode == RESULT_CANCELED) {
            mBinding.rvOtherItems[commonSelectedPosition].concernedIssueImageProgress.visibility = GONE
            mBinding.rvOtherItems[commonSelectedPosition].ivClickConcernedIssueImage.visibility = VISIBLE

        } else if (result.resultCode == RESULT_FIRST_USER) {
            mBinding.rvOtherItems[commonSelectedPosition].concernedIssueImageProgress.visibility = GONE
            mBinding.rvOtherItems[commonSelectedPosition].ivClickConcernedIssueImage.visibility = VISIBLE
            Utils.showPermissionAlertDialog(activity)
        }
    }



    private var modifyImageClickLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            try {
                if (result.data != null) {

                    if (hasActiveInternetConnectionForContext(activity)) {
                        val imageUri = result.data!!.getStringExtra("IMAGE_URI").toString()
                        val file = File(Uri.parse(imageUri).path!!)
                        val requestFile: RequestBody =
                            RequestBody.create(MediaType.parse("multipart/form-data"), file)
                        val body = MultipartBody.Part.createFormData(
                            "file",
                            "IMAGE" + "_" + Utils.getTransactionNumber() + ".png",
                            requestFile
                        )

                        val appointmentIdBody: RequestBody =
                            RequestBody.create(MediaType.parse("text/plain"), appointmentId)

                        val maintenanceIdBody: RequestBody =
                            RequestBody.create(MediaType.parse("text/plain"), serviceId)

                        val maintenanceItemIdBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            modifyItemList[commonSelectedPosition].LineItem.Id.toString())

                        val maintenanceLineIdBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            modifyItemList[commonSelectedPosition].LineId)

                        val descriptionBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            modifyItemList[commonSelectedPosition].LineDesc)

                        val vehicleIdBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            vehicleInformationData.VehicleId
                        )

                        isOtherItemClicked = false
                        postImageViewModel.postMaintenanceImageData(
                            appointmentIdBody,
                            maintenanceIdBody,
                            maintenanceItemIdBody,
                            maintenanceLineIdBody,
                            descriptionBody,
                            vehicleIdBody,
                            body
                        )
                    } else {
                        Toasty.warning(activity, getString(R.string.no_internet_connection)).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (result.resultCode == RESULT_CANCELED) {
            mBinding.rvModifyItems[commonSelectedPosition].concernedIssueImageProgress.visibility =
                View.GONE
            mBinding.rvModifyItems[commonSelectedPosition].ivClickConcernedIssueImage.visibility =
                View.VISIBLE

        } else if (result.resultCode == RESULT_FIRST_USER) {
            mBinding.rvModifyItems[commonSelectedPosition].concernedIssueImageProgress.visibility =
                View.GONE
            mBinding.rvModifyItems[commonSelectedPosition].ivClickConcernedIssueImage.visibility =
                View.VISIBLE
            Utils.showPermissionAlertDialog(activity)
        }
    }



    private fun imageCLickObserver() {
        postImageViewModel.maintenanceImageResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { postSurveyResponse ->
                            isLoading=false
                            ServiceItemsActivity().finish()
                            mBinding.progressBar.visibility  = GONE
                            if(isOtherItemClicked){
                                mBinding.rvOtherItems[commonSelectedPosition].concernedIssueImageProgress.visibility =
                                    GONE
                                mBinding.rvOtherItems[commonSelectedPosition].ivClickConcernedIssueImage.visibility =
                                    VISIBLE
                            }else{
                                mBinding.rvModifyItems[commonSelectedPosition].concernedIssueImageProgress.visibility =
                                    GONE
                                mBinding.rvModifyItems[commonSelectedPosition].ivClickConcernedIssueImage.visibility =
                                    VISIBLE
                            }

                            val concernedImageModel = PdiImageData(
                                postSurveyResponse.Result.Id,
                                postSurveyResponse.Result.OriginalCID,
                                postSurveyResponse.Result.OriginalImageUrl,
                                postSurveyResponse.Result.ThumbnailUrl,
                                postSurveyResponse.Result.IpfsUrl
                            )
                            if(isOtherItemClicked){
                                val childIssueImageList =
                                    otherItemList[commonSelectedPosition].LineImages
                                childIssueImageList.add(concernedImageModel)
                                otherItemList[commonSelectedPosition].LineImages =
                                    childIssueImageList
                                otherItemsAdapter.notifyDataSetChanged()
                            }else{
                                val childIssueImageList =
                                    modifyItemList[commonSelectedPosition].LineImages
                                childIssueImageList.add(concernedImageModel)
                                modifyItemList[commonSelectedPosition].LineImages =
                                    childIssueImageList
                                modifyItemsAdapter.notifyDataSetChanged()
                            }

                        }
                    }
                    is Resource.Error -> {
                        if(isOtherItemClicked){
                            mBinding.rvOtherItems[commonSelectedPosition].concernedIssueImageProgress.visibility =
                                View.GONE
                            mBinding.rvOtherItems[commonSelectedPosition].ivClickConcernedIssueImage.visibility =
                                View.VISIBLE
                        }else{
                            mBinding.rvModifyItems[commonSelectedPosition].concernedIssueImageProgress.visibility =
                                View.GONE
                            mBinding.rvModifyItems[commonSelectedPosition].ivClickConcernedIssueImage.visibility =
                                View.VISIBLE
                        }
                        response.message?.let { message ->

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
                        isLoading=true
                       if(isOtherItemClicked){
                           mBinding.rvOtherItems[commonSelectedPosition].concernedIssueImageProgress.visibility=
                               VISIBLE
                           mBinding.rvOtherItems[commonSelectedPosition].ivClickConcernedIssueImage.visibility =
                               GONE
                       }else{
                           mBinding.rvModifyItems[commonSelectedPosition].concernedIssueImageProgress.visibility=
                               VISIBLE
                           mBinding.rvModifyItems[commonSelectedPosition].ivClickConcernedIssueImage.visibility =
                               GONE
                       }
                    }
                }
            }
        })
    }
    private fun showImageDialog(s: String, originalImageUrl: String, ipfsUrl: String) {
        dialog= Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_images)
        Glide.with(activity)
            .load(originalImageUrl)
            .apply(RequestOptions.centerInsideTransform())
            .placeholder(R.drawable.ic_image_black_24dp)
            .error(R.drawable.error_photo)
            .signature(
                ObjectKey(
                    originalImageUrl + System.currentTimeMillis().toString()
                )
            ).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    dialog.findViewById<ProgressBar>(R.id.pbProgress).visibility = View.GONE
                    return false;
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    dialog.findViewById<ProgressBar>(R.id.pbProgress).visibility = View.GONE
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
                    dialog.findViewById<ImageView>(R.id.ivImage).setBackgroundDrawable(resource)

                }
            })
        dialog.findViewById<TextView?>(R.id.tvTitle).setText(s)
        var close: ImageView =dialog.findViewById(R.id.ivClose)
        close.setOnClickListener {dialog.dismiss()}
        var webView: TextView =dialog.findViewById(R.id.tvIpfs)

        webView.setOnClickListener {
            var intent= Intent(activity.applicationContext,WebViewActivity::class.java)
            intent.putExtra(Constants.WEBVIEW_TITLE,s)
            intent.putExtra(Constants.URL,ipfsUrl)
            activity.startActivity(intent)
        }
        dialog.show()
    }
}