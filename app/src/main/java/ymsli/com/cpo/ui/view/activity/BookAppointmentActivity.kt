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
import kotlinx.android.synthetic.main.activity_book_appointment.*
import kotlinx.android.synthetic.main.list_item_parent_concerned.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.*
import ymsli.com.cpo.databinding.ActivityBookAppointmentBinding
import ymsli.com.cpo.ui.view.adapter.*
import ymsli.com.cpo.ui.viewModel.BookAppointmentViewModel
import ymsli.com.cpo.ui.viewModel.GetNewUidViewModel
import ymsli.com.cpo.ui.viewModel.PostImageViewModel
import ymsli.com.cpo.utils.*
import ymsli.com.cpo.utils.cameraUtils.CameraClickActivity
import java.io.File


@AndroidEntryPoint
class BookAppointmentActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityBookAppointmentBinding
    private var activity: Activity = this@BookAppointmentActivity
    private val tag = "Book Appointment"
    private val vm: BookAppointmentViewModel by viewModels()
    private val getUidViewModel: GetNewUidViewModel by viewModels()
    private val postImageViewModel: PostImageViewModel by viewModels()
    private lateinit var selectDateList: ArrayList<DateResult>
    private lateinit var selectTimeList: ArrayList<String>
    private lateinit var dateAdapter: SelectDateAdapter
    private lateinit var timeAdapter: SelectTimeAdapter
    private var concernedIssueList = ArrayList<ConcernedIssueModel>()
    private lateinit var concernedIssueAdapter: ConcernedIssueAdapter
    private lateinit var tripImageAdapter: TripImagesAdapter
    private var concernedIssueSelectedPosition: Int = 0
    private lateinit var appointmentId: String
    private lateinit var tripMeterLineId: String
    private var tripImageList = ArrayList<TripMeterImagesModel>()
    private lateinit var dialog: Dialog
    private lateinit var vehicleId: String
    private lateinit var dealerId: String
    private lateinit var selectedDate: String
    private lateinit var selectedTime: String
    private lateinit var dealerResult: DealersResult
    private lateinit var vehicleInformationData: VehicleInformationData
    private var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        supportActionBar?.hide()
        initView()
        responseObserver()
        imageClickObserver()
        clickListener()
    }

    private fun initView() {
        getUidViewModel.newUidRequestRequest()
        vehicleInformationData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(
                Constants.VEHICLE_INFORMATION,
                VehicleInformationData::class.java
            )!!
        else
            intent.getSerializableExtra(Constants.VEHICLE_INFORMATION) as VehicleInformationData


        dealerResult = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(
                Constants.DEALER_INFORMATION,
                DealersResult::class.java
            )!!
        else
            intent.getSerializableExtra(Constants.DEALER_INFORMATION) as DealersResult

        vehicleId = vehicleInformationData.VehicleId
        dealerId = dealerResult.Id
        mBinding.item = dealerResult
        mBinding.executePendingBindings()

        tripImageAdapter = TripImagesAdapter(activity, tripImageList, false) {
            if (it[1] == 0) {
                tripImageList.removeAt(it[0])
                tripImageAdapter.notifyDataSetChanged()
            } else {
                showImageDialog("Trip Meter Reading", tripImageList[it[0]].OriginalImageUrl,tripImageList[it[0]].IpfsUrl )
            }
        }
        mBinding.rvTripMeterReadingImages.adapter = tripImageAdapter
    }

    private fun setUpImageClickListener(lineItemId: String) {

        concernedIssueList.add(ConcernedIssueModel("", lineItemId, ArrayList()))

        if (this::concernedIssueAdapter.isInitialized) {
            concernedIssueAdapter.notifyDataSetChanged()
        } else {
            concernedIssueAdapter = ConcernedIssueAdapter(activity, concernedIssueList, {
                concernedIssueList.removeAt(it)
                concernedIssueAdapter.notifyDataSetChanged()
            }, {
                if(!isLoading){
                    concernedIssueSelectedPosition = it
                    if (concernedIssueList[concernedIssueSelectedPosition].issueDetail.isEmpty()) {
                        Toasty.warning(activity, "Please enter issue details").show()
                    } else {
                        val intent = Intent(activity, CameraClickActivity::class.java)
                        issueImageClickLauncher.launch(intent)

                    }
                }else{
                    Toasty.error(activity,"Please wait while image is being loaded").show()
                }


            }, { childList, parentPosition ->
                if (childList[1] == 0) {
                    concernedIssueList[parentPosition].issueImages.removeAt(childList[0])
                    concernedIssueAdapter.notifyDataSetChanged()
                } else {
                    showImageDialog(
                        concernedIssueList[parentPosition].issueDetail,
                        concernedIssueList[parentPosition].issueImages[childList[0]].OriginalImageUrl,
                        concernedIssueList[parentPosition].issueImages[childList[0]].IpfsUrl
                    )
                }
            })

            mBinding.rvConcernedIssue.adapter = concernedIssueAdapter
        }
    }


    private fun clickListener() {
        mBinding.ivBack.setOnClickListener {
            finish()
        }

        mBinding.addConcernedIssue.setOnClickListener {
            if (concernedIssueList[concernedIssueList.size - 1].issueImages.size == 0 && concernedIssueList[concernedIssueList.size - 1].issueDetail.isEmpty()) {
                Toasty.warning(activity, "Please enter issue detail").show()
            } else {
                getUidViewModel.lineItemRequestRequest()
            }
        }


        mBinding.ivClickTripMasterImage.setOnClickListener {
            if (mBinding.etTripMeterReading.text.isEmpty()) {
                Toasty.warning(activity, "Please enter trip meter reading").show()
            } else {
                val intent = Intent(activity, CameraClickActivity::class.java)
                tripMeterImageClickLauncher.launch(intent)
            }
        }
        mBinding.btnBookAppointment.setOnClickListener {
            //Check Validation
            if(isLoading){
                Toasty.warning(activity.baseContext,R.string.while_loading).show()
            }
            else{
                var valid:Boolean=true
                for(i in concernedIssueList){
                    if(i.issueDetail.isNullOrEmpty()){
                        valid=false
                        break
                    }
                }
                if(valid){
                    if (formValidation()) {
                        configureForPreviewPage()
                    }
                }else{
                    Toasty.warning(activity,"One or more issue details are empty").show()
                }
            }
        }
    }

    private fun formValidation(): Boolean {
        var validationStatus = false

        if (!this::selectedDate.isInitialized) {
            Toasty.warning(activity, "Please Select Date").show()
        } else if (!this::selectedTime.isInitialized) {
            Toasty.warning(activity, "Please Select Time Slot").show()
        } else if (concernedIssueList[0].issueImages.size == 0) {
            Toasty.warning(activity, "Please enter your concerned issues").show()
        } else if (tripImageList.size == 0) {
            Toasty.warning(activity, "Please enter your trip meter reading").show()
        } else if (mBinding.etTripMeterReading.text.toString()==""){
            Toasty.warning(activity,"Please enter Trip meter reading").show()
        } else {
            validationStatus = true
        }
        return validationStatus;

    }

    private fun configureForPreviewPage() {
        if (mBinding.btnBookAppointment.text == "Next") {

            mBinding.labelSelectDate.visibility = GONE
            mBinding.labelSelectTime.visibility = GONE
            mBinding.rvSelectDate.visibility = GONE
            mBinding.rvSelectTime.visibility = GONE
            mBinding.labelSelectedDate.visibility = VISIBLE
            mBinding.tvSelectedDate.text = selectedDate.split("T")[0]
            mBinding.tvSelectedTime.text = selectedTime
            mBinding.llSelectedDate.visibility = VISIBLE

            mBinding.addConcernedIssue.visibility = GONE
            mBinding.etTripMeterReading.isEnabled = false
            mBinding.cardClickTripMeterImage.visibility = GONE

            tripImageAdapter.changeNftValue(true)
            tripImageAdapter.notifyDataSetChanged()

            concernedIssueAdapter.changeNftValue(true)
            concernedIssueAdapter.notifyDataSetChanged()
            mBinding.btnBookAppointment.text = "Confirm"

            Log.e("Concerned Issue List: ",concernedIssueList.toString())
        } else {
            //All thing are set now execute the API

            val issueIdList = ArrayList<RequestBodies.LineItemRequest>()

            val tripIdImagesList = ArrayList<String>()


            for(tripImagesItem in tripImageList){
                tripIdImagesList.add(tripImagesItem.Id)
            }

            val tripIdList = RequestBodies.LineItemRequest(tripMeterLineId,mBinding.etTripMeterReading.text.toString(),tripIdImagesList)


            for (concernIssueItem in concernedIssueList) {
                val issueIdImagesList = ArrayList<String>()
                for(concernedImageItem in concernIssueItem.issueImages)
                {
                    issueIdImagesList.add(concernedImageItem.Id)
                }
                issueIdList.add(RequestBodies.LineItemRequest(concernIssueItem.LineId,concernIssueItem.issueDetail,issueIdImagesList))

            }


            Log.e("IssueIdList: ",issueIdList.toString())
            Log.e("TripIdList: ",tripIdList.toString())
            val appointmentRequestBody = RequestBodies.SubmitAppointRequestBody(
                appointmentId,
                issueIdList,
                selectedDate,
                dealerId,
                selectedTime,
                tripIdList,
                vehicleId
            )
            vm.appointmentRequest(appointmentRequestBody)
        }
    }

    private fun responseObserver() {
        getUidViewModel.newUidResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            appointmentId = it.Result.toString()
                            getUidViewModel.tripMeterLineRequest()
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
                        mBinding.progressBar.visibility = VISIBLE
                    }
                }
            }
        })

        getUidViewModel.tripMeterLineIdResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            tripMeterLineId = it.Result.toString()
                            getUidViewModel.lineItemRequestRequest()
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
                        mBinding.progressBar.visibility = VISIBLE
                    }
                }
            }
        })

        getUidViewModel.lineItemUidResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            if (this::concernedIssueAdapter.isInitialized) {
                                setUpImageClickListener(it.Result.toString())
                            } else {
                                vm.dateRequest()
                                setUpImageClickListener(it.Result.toString())
                            }


                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = GONE
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
                        // Loading is already started in appointment id request
                        // mBinding.progressBar.visibility = VISIBLE
                    }
                }
            }
        })

        vm.selectDateResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let {
                            Utils.logThis(tag, "Date Response  is :   ${it.Result}")
                            selectDateList = it.Result as ArrayList<DateResult>
                            dateAdapter = SelectDateAdapter(activity, selectDateList) { _position ->

                                selectDateList.forEachIndexed { i, element ->
                                    if (i == _position) {
                                        selectDateList[i].status = "1"
                                    } else {
                                        selectDateList[i].status = "0"
                                    }
                                }

                                dateAdapter.notifyDataSetChanged()
                                selectedDate = selectDateList[_position].Date
                            }
                            mBinding.rvSelectDate.adapter = dateAdapter
                            vm.timeRequest()
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
                        //Loading is already started
                        // mBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })

        vm.selectTimeResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = GONE
                        response.data?.let {
                            Utils.logThis(tag, "Time Response  is :   ${it.Result}")
                            selectTimeList = it.Result as ArrayList<String>
                            timeAdapter = SelectTimeAdapter(activity, selectTimeList) { _position ->
                                selectedTime = selectTimeList[_position]
                            }
                            mBinding.rvSelectTime.adapter = timeAdapter
                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = GONE
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
                        //  Loading already started in date request
                        //  mBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })

        vm.submitAppointmentResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = GONE
                        response.data?.let {
                            Utils.logThis(tag, "Submit Appointment API  Response  is :   ${it}")
                            val message = it.SuccessMessage.toString()
                            CancelableToast(activity, message, false).success()
                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = GONE
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
                        //  Loading already started in date request
                        //  mBinding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }


    /**
     * Concerned Image Click launcher
     * */

    private var issueImageClickLauncher = registerForActivityResult(
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
                            concernedIssueList[concernedIssueSelectedPosition].LineId
                        )
                        val descriptionBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            concernedIssueList[concernedIssueSelectedPosition].issueDetail
                        )

                        postImageViewModel.postConcernedIssueImageData(
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
            mBinding.rvConcernedIssue[concernedIssueSelectedPosition].concernedIssueImageProgress.visibility =
                GONE
            mBinding.rvConcernedIssue[concernedIssueSelectedPosition].ivClickConcernedIssueImage.visibility =
                VISIBLE

        } else if (result.resultCode == RESULT_FIRST_USER) {
            mBinding.rvConcernedIssue[concernedIssueSelectedPosition].concernedIssueImageProgress.visibility =
                GONE
            mBinding.rvConcernedIssue[concernedIssueSelectedPosition].ivClickConcernedIssueImage.visibility =
                VISIBLE
            Utils.showPermissionAlertDialog(activity)
        }
    }


    /**
     * Concerned Image Click launcher
     * */

    private var tripMeterImageClickLauncher = registerForActivityResult(
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
                        val appointmentLineItemIdBody: RequestBody =
                            RequestBody.create(MediaType.parse("text/plain"), tripMeterLineId)
                        val descriptionBody: RequestBody = RequestBody.create(
                            MediaType.parse("text/plain"),
                            mBinding.etTripMeterReading.text.toString()
                        )
                        postImageViewModel.postTripMeterImageData(
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
            //User does not click the photos
        } else if (result.resultCode == RESULT_FIRST_USER) {
            //User does not give permission
            Utils.showPermissionAlertDialog(activity)
        }
    }


    /**
     * Observe Data when you click image for both issues and trip meter reading
     * */

    private fun imageClickObserver() {

        postImageViewModel.concernedIssueImageResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { postSurveyResponse ->
                            isLoading=false
                            mBinding.rvConcernedIssue[concernedIssueSelectedPosition].concernedIssueImageProgress.visibility =
                                GONE
                            mBinding.rvConcernedIssue[concernedIssueSelectedPosition].ivClickConcernedIssueImage.visibility =
                                VISIBLE

                            val concernedImageModel = ConcernedImagesModel(
                                postSurveyResponse.Result.Id,
                                postSurveyResponse.Result.OriginalImageUrl,
                                postSurveyResponse.Result.ThumbnailUrl,
                                postSurveyResponse.Result.IpfsUrl
                            )

                            val childIssueImageList =
                                concernedIssueList[concernedIssueSelectedPosition].issueImages
                            childIssueImageList.add(concernedImageModel)
                            concernedIssueList[concernedIssueSelectedPosition].issueImages =
                                childIssueImageList
                            concernedIssueAdapter.notifyDataSetChanged()
                        }
                    }

                    is Resource.Error -> {
                        mBinding.rvConcernedIssue[concernedIssueSelectedPosition].concernedIssueImageProgress.visibility =
                            GONE
                        mBinding.rvConcernedIssue[concernedIssueSelectedPosition].ivClickConcernedIssueImage.visibility =
                            VISIBLE
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
                        isLoading=true
                        mBinding.rvConcernedIssue[concernedIssueSelectedPosition].concernedIssueImageProgress.visibility =
                            VISIBLE
                        mBinding.rvConcernedIssue[concernedIssueSelectedPosition].ivClickConcernedIssueImage.visibility =
                            GONE
                    }
                }
            }
        })

        postImageViewModel.tripMeterImageResponse.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { postSurveyResponse ->
                            isLoading=false
                            mBinding.tripMasterImageProgress.visibility = GONE
                            mBinding.ivClickTripMasterImage.visibility = VISIBLE


                            val tripMeterImagesModel = TripMeterImagesModel(
                                postSurveyResponse.Result.Id,
                                postSurveyResponse.Result.OriginalImageUrl,
                                postSurveyResponse.Result.ThumbnailUrl,
                                postSurveyResponse.Result.IpfsUrl
                            )


                            tripImageList.add(tripMeterImagesModel)
                            tripImageAdapter.notifyDataSetChanged()
                            showHideTripRecyclerView()
                        }
                    }

                    is Resource.Error -> {
                        mBinding.tripMasterImageProgress.visibility = GONE
                        mBinding.ivClickTripMasterImage.visibility = VISIBLE
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
                        isLoading=true
                        mBinding.tripMasterImageProgress.visibility = VISIBLE
                        mBinding.ivClickTripMasterImage.visibility = GONE
                    }
                }
            }
        })

    }

    private fun showHideTripRecyclerView() {
        if (tripImageList.size > 0) {
            mBinding.rvTripMeterReadingImages.visibility = VISIBLE
        } else {
            mBinding.rvTripMeterReadingImages.visibility = GONE
        }
    }


    private fun showImageDialog(s: String, originalImageUrl: String, ipfsUrl: String) {
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
                    dialog.findViewById<ProgressBar>(R.id.pbProgress).visibility = GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    dialog.findViewById<ProgressBar>(R.id.pbProgress).visibility = GONE
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
        dialog.findViewById<TextView?>(R.id.tvIpfs).setOnClickListener {
            var intent=Intent()
        }
        val close: ImageView = dialog.findViewById(R.id.ivClose)
        close.setOnClickListener { dialog.dismiss() }
        var webView: TextView =dialog.findViewById(R.id.tvIpfs)

        webView.setOnClickListener {
            if(ipfsUrl!=""){
                var intent= Intent(activity.applicationContext,WebViewActivity::class.java)
                intent.putExtra(Constants.WEBVIEW_TITLE,s)
                intent.putExtra(Constants.URL,ipfsUrl)
                activity.startActivity(intent)
            }else{
                Toasty.error(activity,"Can't load please try again later").show()
            }

        }
        dialog.show()
    }


}
