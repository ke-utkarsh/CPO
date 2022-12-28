package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
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
import kotlinx.android.synthetic.main.item_list_service_submit.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.*
import ymsli.com.cpo.databinding.ActivityServiceSubmitBinding
import ymsli.com.cpo.ui.view.adapter.ServiceSubmitParentAdapter
import ymsli.com.cpo.ui.viewModel.PostImageViewModel
import ymsli.com.cpo.ui.viewModel.ServiceSubmitViewModel
import ymsli.com.cpo.utils.*
import ymsli.com.cpo.utils.cameraUtils.CameraClickActivity
import java.io.File
import kotlin.collections.ArrayList
import kotlin.collections.List

@AndroidEntryPoint
class ServiceSubmitActivity :AppCompatActivity(){
    private lateinit var mBinding : ActivityServiceSubmitBinding
    private var activity: Activity = this@ServiceSubmitActivity
    private val postImageViewModel: PostImageViewModel by viewModels()
    private val serviceViewModel : ServiceSubmitViewModel by viewModels()
    private lateinit var beforeList: List<Concerned>
    private lateinit var serviceSubmitParentAdapter :ServiceSubmitParentAdapter
    private var commonSelectedPosition:Int=0
    private lateinit var appointmentId: String
    private lateinit var dialog: Dialog
    //private var afterImageList= ArrayList<AfterImagesListModel>()
    private lateinit var afterImageList2 : List<Concerned>
    private lateinit var maintenanceId : String
    private lateinit var vehicleId : String
    private var afterImageListRequest = ArrayList<RequestBodies.MaintenanceLinesConcern>()
    private var isSave=true;
    private var isLoading=false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityServiceSubmitBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.hide()
        initView()
        onClick()
        responseObserver()
        imageCLickObserver()

    }

    private fun initView() {
        appointmentId = intent.getStringExtra("appointmentId").toString()
        mBinding.tvPageHead.text = "Service Request :" + intent.getStringExtra("service_request_id").toString().split("-")[4].toUpperCase()

        Log.e("Appointment ID",appointmentId)
        Log.e("Submit",intent.getStringExtra("service_request_id").toString())

        serviceViewModel.getServiceRequestDetails(RequestBodies.GetAppointmentDetail(appointmentId))

    }

    private fun onClick(){
        mBinding.btnServiceSubmit.setOnClickListener{
            if(isLoading){
                Toasty.warning(activity.baseContext,R.string.while_loading).show()
            }else{
                isSave=false
                if(formValidation() && !mBinding.etamount.text.toString().equals("")){
                    var lineIdList = ArrayList<String>()
                    for(afterImageItem in afterImageList2){
                        for(item in afterImageItem.LineImages){
                            lineIdList.add(item.Id)
                        }
                        val temp = RequestBodies.MaintenanceLinesConcern(afterImageItem.LineId,lineIdList)
                        afterImageListRequest.add(temp)
                    }

                    serviceViewModel.ServiceSubmitCompleteRequest(RequestBodies.SubmitCompleteRequest(true,maintenanceId,afterImageListRequest,mBinding.etamount.text.toString().toInt(),vehicleId))
                }
            }
        }

        mBinding.btnSave.setOnClickListener {
            if(isLoading){
                Toasty.warning(activity.baseContext,R.string.while_loading).show()
            }else{
                isSave=true
                if(formValidation() && !mBinding.etamount.text.toString().equals("")){
                    var lineIdList = ArrayList<String>()
                    for(afterImageItem in afterImageList2){
                        for(item in afterImageItem.LineImages){
                            lineIdList.add(item.Id)
                        }
                        val temp = RequestBodies.MaintenanceLinesConcern(afterImageItem.LineId,lineIdList)
                        afterImageListRequest.add(temp)
                    }
                    serviceViewModel.ServiceSubmitCompleteRequest(RequestBodies.SubmitCompleteRequest(false,maintenanceId,afterImageListRequest,mBinding.etamount.text.toString().toInt(),vehicleId))

                }
            }
        }

        mBinding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun responseObserver() {

        serviceViewModel.serviceSubmitResponse.observe(this , androidx.lifecycle.Observer{
            it.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { getBeforeImagesModel->
                            mBinding.progressBar.visibility = View.GONE
                            beforeList = getBeforeImagesModel.Result.Concerend
                            afterImageList2 = getBeforeImagesModel.Result.MaintenanceConcerned
                            maintenanceId = getBeforeImagesModel.Result.MaintenanceId
                            vehicleId = getBeforeImagesModel.Result.VehicleId
                            /* for (i in 1..beforeList.size){
                                 var temp= ArrayList<PdiImageData>()
                                 afterImageList.add(AfterImagesListModel(temp))
                             }*/

                            serviceSubmitParentAdapter = ServiceSubmitParentAdapter(activity,beforeList,afterImageList2,{
                                if(!isLoading){
                                    commonSelectedPosition = it
                                    val intent = Intent(activity, CameraClickActivity::class.java)
                                    modifyImageClickLauncher.launch(intent)
                                }else{
                                    Toasty.error(activity,"Please wait while image is being loaded").show()
                                }

                            },{childList, parentPosition ->
                                if (childList[1] == 0) {
                                    afterImageList2[parentPosition].LineImages.removeAt(childList[0])
                                    serviceSubmitParentAdapter.notifyDataSetChanged()
                                } else if(childList[1]==2) {
                                    showImageDialog(
                                        afterImageList2[parentPosition].LineDesc,
                                        afterImageList2[parentPosition].LineImages[childList[0]].OriginalImageUrl,
                                        afterImageList2[parentPosition].LineImages[childList[0]].IpfsUrl
                                    )
                                }

                            })
                            mBinding.rvServiceItems.adapter = serviceSubmitParentAdapter

                        }
                    }
                    is Resource.Error -> {

                        Toasty.warning(activity,"Error while Calling API")
                        /*mBinding.rvServiceItems[commonSelectedPosition].beforeImageProgress.visibility =
                            View.GONE
                        mBinding.rvServiceItems[commonSelectedPosition].ivClickBeforeImage.visibility =
                            View.VISIBLE
                        response.message?.let { message ->

                        }*/
                    }
                    is Resource.Loading -> {
                        mBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })

        serviceViewModel.serviceSubmitCompleteResponse.observe(this, Observer {
            it.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { getBeforeImagesModel->
                            mBinding.progressBar.visibility = View.GONE

                            Log.e("Service Submit Completed: ",response.toString())
                            if(isSave){
                                Toasty.info(activity,"Saved!, Press Submit to submit service").show()
                            }else{
                                var intent=Intent(applicationContext,MintNftSuccessActivity::class.java)
                                intent.putExtra(Constants.MESSAGE, "Service submitted successfully")
                                intent.putExtra(Constants.MAINTENANCE_ID, maintenanceId)
                                startActivity(intent)
                                finish()
                            }

                        }
                    }
                    is Resource.Error -> {

                        response.message?.let { message ->
                            if (message == "Unauthorized") {
                                CancelableToast(
                                    activity,
                                    resources.getString(R.string.unauthorized_error)
                                ).alert()
                                Utils.sessionTimeOutStartLoginScreen(activity)
                            } else {
                                CancelableToast(activity, message,false).error()
                            }
                        }
                        mBinding.progressBar.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        mBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })

    }


    private fun imageCLickObserver() {
        postImageViewModel.afterImageResponse.observe(this , androidx.lifecycle.Observer { it ->
            it.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { afterImageResponse ->
                            isLoading=false
                            mBinding.rvServiceItems[commonSelectedPosition].beforeImageProgress.visibility=GONE
                            mBinding.rvServiceItems[commonSelectedPosition].ivClickBeforeImage.visibility=
                                VISIBLE


                            Log.e("Position",commonSelectedPosition.toString())
                            Log.e("Position",afterImageResponse.toString())

                            /*f(afterImageList.size<=commonSelectedPosition){
                                var temp2= ArrayList<PdiImageData>()
                                temp2.add(afterImageResponse.Result)
                                val temp = AfterImagesListModel(temp2)
                                afterImageList.add(temp)
                            }
                            else{
                                afterImageList[commonSelectedPosition].issueImages.add(afterImageResponse.Result)

                            }*/
                            var temp = LineImage(afterImageResponse.Result.Id,afterImageResponse.Result.IpfsUrl,afterImageResponse.Result.OriginalCID,afterImageResponse.Result.OriginalImageUrl,afterImageResponse.Result.ThumbnailUrl)
                            afterImageList2[commonSelectedPosition].LineImages.add(temp)
                            serviceSubmitParentAdapter.notifyDataSetChanged()


                        }
                    }
                    is Resource.Error -> {

                        mBinding.rvServiceItems[commonSelectedPosition].beforeImageProgress.visibility =
                            View.GONE
                        mBinding.rvServiceItems[commonSelectedPosition].ivClickBeforeImage.visibility =
                            View.VISIBLE
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
                        mBinding.rvServiceItems[commonSelectedPosition].beforeImageProgress.visibility =
                            View.VISIBLE
                    }
                }
            }
        })
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
                        val appointmentLineItemIdBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            beforeList[commonSelectedPosition].LineId
                        )
                        val descriptionBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            beforeList[commonSelectedPosition].LineDesc
                        )

                        postImageViewModel.afterImageData(
                            appointmentIdBody,
                            appointmentLineItemIdBody,
                            descriptionBody,
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
            mBinding.rvServiceItems[commonSelectedPosition].beforeImageProgress.visibility =
                View.GONE
            mBinding.rvServiceItems[commonSelectedPosition].ivClickBeforeImage.visibility =
                View.VISIBLE

        } else if (result.resultCode == RESULT_FIRST_USER) {
            mBinding.rvServiceItems[commonSelectedPosition].beforeImageProgress.visibility =
                View.GONE
            mBinding.rvServiceItems[commonSelectedPosition].ivClickBeforeImage.visibility =
                View.VISIBLE
            Utils.showPermissionAlertDialog(activity)
        }
    }



    private fun showImageDialog(s: String, originalImageUrl: String, ipfsurl: String) {
        dialog = Dialog(activity)
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
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    dialog.findViewById<ProgressBar>(R.id.pbProgress).visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
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
        dialog.findViewById<TextView?>(R.id.tvTitle).text = s
        val close: ImageView = dialog.findViewById(R.id.ivClose)
        close.setOnClickListener { dialog.dismiss() }
        var webView: TextView =dialog.findViewById(R.id.tvIpfs)
        webView.setOnClickListener {
            var intent= Intent(activity.applicationContext,WebViewActivity::class.java)
            intent.putExtra(Constants.WEBVIEW_TITLE,s)
            intent.putExtra(Constants.URL,ipfsurl)
            activity.startActivity(intent)
        }
        dialog.show()
    }

    private fun formValidation(): Boolean {
        var validationStatus = false

        for (afterimage in afterImageList2){
            if(afterimage.LineImages.size==0){
                Toasty.warning(activity, "Atleast one image is required for after").show()
                return false
            }
        }
        if(mBinding.etamount.text.toString().equals("")) {
            Toasty.warning(activity, "Please enter amount").show()
            return false
        }
        else
            validationStatus = true

        return validationStatus;

    }

}