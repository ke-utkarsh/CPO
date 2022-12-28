package ymsli.com.cpo.ui.view.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
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
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
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
import es.dmoral.toasty.Toasty
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.PdiImage
import ymsli.com.cpo.data.model.VehiclePhoto
import ymsli.com.cpo.databinding.FragmentVehicalPhotosBinding
import ymsli.com.cpo.ui.view.activity.WarrantiesActivity
import ymsli.com.cpo.ui.view.activity.WebViewActivity
import ymsli.com.cpo.ui.view.adapter.PdiImagesAdapter
import ymsli.com.cpo.ui.view.adapter.VehicleImagesAdapter
import ymsli.com.cpo.ui.viewModel.PostImageViewModel
import ymsli.com.cpo.ui.viewModel.VehicleInformationViewModel
import ymsli.com.cpo.utils.*
import ymsli.com.cpo.utils.cameraUtils.CameraClickActivity
import java.io.File

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentVehiclePhotosDealer.newInstance] factory method to
 * create an instance of this fragment.
 */

class FragmentVehiclePhotosDealer : Fragment() {
    private val vm: VehicleInformationViewModel by activityViewModels()
    private lateinit var mBinding: FragmentVehicalPhotosBinding
    private var pdiImageList: ArrayList<PdiImage> = ArrayList()
    private var vehiclePhotoList: ArrayList<VehiclePhoto> = ArrayList()
    private var pdiIdList: ArrayList<String> = ArrayList()
    private var vehicleIdList: ArrayList<String> = ArrayList()
    private var pdiImageUrl = "NA"
    private var vehicleImageUrl = "NA"
    var imageRotationAngle = 0f
    private val postImageViewModel: PostImageViewModel by activityViewModels()
    private lateinit var pdiImageAdapter: PdiImagesAdapter
    private lateinit var vehicleImageAdapter: VehicleImagesAdapter
    lateinit var vehicleMasterId: String
    private lateinit var dialog: Dialog
    private var isLoading=false;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentVehicalPhotosBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getObserveData()
        clickListener()
    }

    private fun init() {
        pdiImageAdapter = PdiImagesAdapter(requireContext(), pdiImageList, false) {it->
            if(it[1]==0){
                pdiImageList.removeAt(it[0])
                pdiImageAdapter.notifyDataSetChanged()
            }else{
                showImageDialog("PDI", pdiImageList[it[0]].OriginalImageUrl, pdiImageList[it[0]].IpfsUrl)
            }

        }
        mBinding.rvPdiImages.adapter = pdiImageAdapter

        vehicleImageAdapter = VehicleImagesAdapter(requireContext(), vehiclePhotoList, false) {it->
            if(it[1]==0){
                vehiclePhotoList.removeAt(it[0])
                vehicleImageAdapter.notifyDataSetChanged()
            }else{
                showImageDialog("Vehicle Photo",vehiclePhotoList.get(it[0]).OriginalImageUrl,vehiclePhotoList.get(it[0]).IpfsUrl)
            }

        }
        mBinding.rvVehicleImages.adapter = vehicleImageAdapter
    }

    private fun showImageDialog(s: String, originalImageUrl: String, ipfsUrl:String) {
        dialog= Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_images)
        Glide.with(requireContext())
            .load(originalImageUrl)
            .apply(RequestOptions.centerInsideTransform())
            .placeholder(R.drawable.ic_image_black_24dp)
            .error(R.drawable.error_photo)
            .signature(
                ObjectKey(
                    originalImageUrl + System.currentTimeMillis().toString()
                )
            ).listener(object :
           RequestListener<Drawable>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                dialog.findViewById<ProgressBar>(R.id.pbProgress).visibility=View.GONE
                return false;
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                dialog.findViewById<ProgressBar>(R.id.pbProgress).visibility=View.GONE
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
        var close:ImageView=dialog.findViewById(R.id.ivClose)
        close.setOnClickListener {dialog.dismiss()}
        var webView: TextView =dialog.findViewById(R.id.tvIpfs)

        webView.setOnClickListener {
            var intent= Intent(activity, WebViewActivity::class.java)
            intent.putExtra(Constants.WEBVIEW_TITLE,s)
            intent.putExtra(Constants.URL,ipfsUrl)
            startActivity(intent)
        }
        dialog.show()
    }


    private fun clickListener() {
        mBinding.ivClickPdiImage.setOnClickListener {
            pdiImageUrl = "NA"
            val `in` = Intent(requireContext(), CameraClickActivity::class.java)
            pdiImageClickLauncher.launch(`in`)
        }

        mBinding.ivClickVehicleImage.setOnClickListener {
            vehicleImageUrl = "NA"
            val `in` = Intent(requireContext(), CameraClickActivity::class.java)
            vehicleImageClickLauncher.launch(`in`)
        }

        mBinding.btnSubmit.setOnClickListener {
            if(isLoading){
                context?.let { it1 -> Toasty.warning(it1,R.string.while_loading).show() }
            }else{
                if(pdiImageList.isEmpty() || vehiclePhotoList.isEmpty()){
                    context?.let { it1 -> Toasty.warning(it1,"Click atleast one picture in all fields").show() }
                }else{
                    for (pdiData in pdiImageList) {
                        pdiIdList.add(pdiData.Id)
                    }
                    for (imgData in vehiclePhotoList) {
                        vehicleIdList.add(imgData.Id)
                    }

                    vm.submitNFTRequest(
                        RequestBodies.SubmitNftDataBody(
                            vehicleMasterId,
                            pdiIdList,
                            vehicleIdList
                        )
                    )
                }
            }
        }
    }

    private fun showHideImage() {
        if (pdiImageList.size == 0) {
            mBinding.rvPdiImages.visibility = GONE
        } else {
            mBinding.rvPdiImages.visibility = VISIBLE
        }

        if (vehiclePhotoList.size == 0) {
            mBinding.rvVehicleImages.visibility = GONE
        } else {
            mBinding.rvVehicleImages.visibility = VISIBLE
        }
    }


    /**
     * Here posted image data is observed
     * */
    private fun getObserveData() {

        vm.submitNFTResponse.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        mBinding.progressBar.visibility = GONE
                        response.data?.let { responseData ->
                            val message = responseData.SuccessMessage.toString()
                            CancelableToast(requireActivity(), message, false).success()
                        }
                    }
                    is Resource.Error -> {
                        mBinding.progressBar.visibility = GONE
                        response.message?.let { responseData ->
                            val message = responseData
                            CancelableToast(requireActivity(), message).error()
                        }
                    }
                    is Resource.Loading -> {
                        mBinding.progressBar.visibility = VISIBLE
                    }
                }
            }
        })

        vm.vehicleInformationResponse.observe(viewLifecycleOwner, Observer { event ->
            event.peekContent().let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { vehicleInformation ->
                            vehicleMasterId = vehicleInformation.Result.VehicleMasterId
                            for (pdiIMages in vehicleInformation.Result.PdiImages) {
                                val pdiImageModel = PdiImage(
                                    pdiIMages.Id,
                                    pdiIMages.OriginalImageUrl,
                                    pdiIMages.OriginalCID,
                                    pdiIMages.ThumbnailUrl,
                                    pdiIMages.IpfsUrl

                                )
                                pdiImageList.add(pdiImageModel)
                            }
                            pdiImageAdapter.notifyDataSetChanged()


                            for (vehicleIMages in vehicleInformation.Result.VehiclePhotos) {
                                val vehiclePhotoModel = VehiclePhoto(
                                    vehicleIMages.Id,
                                    vehicleIMages.OriginalImageUrl,
                                    vehicleIMages.OriginalCID,
                                    vehicleIMages.ThumbnailUrl,
                                    vehicleIMages.IpfsUrl
                                )
                                vehiclePhotoList.add(vehiclePhotoModel)
                            }
                            vehicleImageAdapter.notifyDataSetChanged()

                            showHideImage()
                        }
                    }
                    else -> {}
                }
            }
        })


        postImageViewModel.postPdiImageResponse.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { postSurveyResponse ->
                            isLoading=false
                            Utils.logThis(
                                tag,
                                "Post Image response is : " + postSurveyResponse.Result
                            )

                            pdiImageUrl = postSurveyResponse.Result.OriginalImageUrl
                            val pdiImageModel = PdiImage(
                                postSurveyResponse.Result.Id,
                                postSurveyResponse.Result.OriginalImageUrl,
                                postSurveyResponse.Result.OriginalCID,
                                postSurveyResponse.Result.ThumbnailUrl,
                                postSurveyResponse.Result.IpfsUrl
                            )

                            pdiImageList.add(pdiImageModel)
                            showHideImage()
                            pdiImageAdapter.notifyDataSetChanged()
                            mBinding.ivClickPdiImage.visibility = VISIBLE
                            mBinding.pdiImageProgress.visibility = GONE
                            context?.let { it1 ->
                                Toasty.success(
                                    it1,
                                    postSurveyResponse.SuccessMessage,
                                    Toast.LENGTH_LONG,
                                    true
                                ).show()
                            }
                        }
                    }

                    is Resource.Error -> {
                        mBinding.ivClickPdiImage.visibility = VISIBLE
                        mBinding.pdiImageProgress.visibility = GONE
                        response.message?.let { message ->
                            Utils.logThis(tag, "Error message is : $message")
                            if (message == "Unauthorized") {
                                CancelableToast(
                                    requireActivity(),
                                    resources.getString(R.string.unauthorized_error)
                                ).alert()
                                Utils.sessionTimeOutStartLoginScreen(requireActivity())
                            } else {
                                CancelableToast(requireActivity(), message).error()
                            }
                        }
                    }
                    is Resource.Loading -> {
                        isLoading=true
                        mBinding.pdiImageProgress.visibility = VISIBLE
                        mBinding.ivClickPdiImage.visibility = GONE
                    }
                }
            }
        })


        postImageViewModel.postVehicleImageResponse.observe(viewLifecycleOwner, Observer { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { postSurveyResponse ->
                            isLoading=false
                            Utils.logThis(
                                tag,
                                "Post Vehicle Image response is : " + postSurveyResponse.Result
                            )

                            vehicleImageUrl = postSurveyResponse.Result.OriginalImageUrl
                            val vehicleImageModel = VehiclePhoto(
                                postSurveyResponse.Result.Id,
                                postSurveyResponse.Result.OriginalImageUrl,
                                postSurveyResponse.Result.OriginalCID,
                                postSurveyResponse.Result.ThumbnailUrl,
                                postSurveyResponse.Result.IpfsUrl
                            )
                            vehiclePhotoList.add(vehicleImageModel)
                            showHideImage()
                            vehicleImageAdapter.notifyDataSetChanged()
                            mBinding.ivClickVehicleImage.visibility = VISIBLE
                            mBinding.vehicleImageProgress.visibility = GONE
                            context?.let { it1 ->
                                Toasty.success(
                                    it1,
                                    postSurveyResponse.SuccessMessage,
                                    Toast.LENGTH_LONG,
                                    true
                                ).show()

                            }

                        }
                    }
                    is Resource.Error -> {
                        mBinding.ivClickVehicleImage.visibility = VISIBLE
                        mBinding.vehicleImageProgress.visibility = GONE
                        response.message?.let { message ->
                            Utils.logThis("jeev", "Error message is : $message")
                            if (message == "Unauthorized") {
                                CancelableToast(
                                    requireActivity(),
                                    resources.getString(R.string.unauthorized_error)
                                ).alert()
                                Utils.sessionTimeOutStartLoginScreen(requireActivity())
                            } else {
                                CancelableToast(requireActivity(), message).error()
                            }
                        }
                    }
                    is Resource.Loading -> {
                        isLoading=true
                        mBinding.vehicleImageProgress.visibility = VISIBLE
                        mBinding.ivClickVehicleImage.visibility = GONE
                    }
                }
            }
        })

    }


    //  ===================================== Image Click Launcher ===============================-->

    private var pdiImageClickLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            try {
                if (result.data != null) {
                    if (hasActiveInternetConnectionForContext(requireContext())) {
                        val imageUri = result.data!!.getStringExtra("IMAGE_URI").toString()
                        imageRotationAngle =
                            result.data!!.getIntExtra("IMAGE_ROTATION", 0).toFloat()
                        val file = File(Uri.parse(imageUri).path!!)

                        val requestFile: RequestBody =

                            RequestBody.create(MediaType.parse("multipart/form-data"), file)

                        val body = MultipartBody.Part.createFormData(
                            "file",
                            "IMAGE" + "_" + Utils.getTransactionNumber() + ".png",
                            requestFile
                        )

                        val requestVehicleMasterID: RequestBody =
                            RequestBody.create(MediaType.parse("text/plain"), vehicleMasterId)


                        postImageViewModel.postPdiImageData(requestVehicleMasterID, body)
                    } else {
                        Toasty.warning(
                            requireActivity(),
                            getString(R.string.no_internet_connection)
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (result.resultCode == AppCompatActivity.RESULT_CANCELED) {
            mBinding.pdiImageProgress.visibility = GONE
            if (pdiImageUrl.equals("NA", ignoreCase = true)) {
                mBinding.ivClickPdiImage.visibility = VISIBLE
            } else {
                mBinding.ivClickPdiImage.visibility = GONE
            }
        } else if (result.resultCode == AppCompatActivity.RESULT_FIRST_USER) {
            mBinding.pdiImageProgress.visibility = GONE
            if (pdiImageUrl.equals("NA", ignoreCase = true)) {
                mBinding.ivClickPdiImage.visibility = VISIBLE
            } else {
                GONE.also { mBinding.ivClickPdiImage.visibility = it }
            }
            Utils.showPermissionAlertDialog(requireActivity())
        }
    }


    //  ===================================== Image Click Launcher ===============================-->

    private var vehicleImageClickLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            try {
                if (result.data != null) {
                    if (hasActiveInternetConnectionForContext(requireContext())) {
                        val imageUri = result.data!!.getStringExtra("IMAGE_URI").toString()
                        imageRotationAngle =
                            result.data!!.getIntExtra("IMAGE_ROTATION", 0).toFloat()
                        val file = File(Uri.parse(imageUri).path!!)
                        val requestFile: RequestBody =
                            RequestBody.create(MediaType.parse("multipart/form-data"), file)
                        val body = MultipartBody.Part.createFormData(
                            "file",
                            "IMAGE" + "_" + Utils.getTransactionNumber() + ".png",
                            requestFile
                        )
                        val requestVehicleMasterID: RequestBody =
                            RequestBody.create(MediaType.parse("text/plain"), vehicleMasterId)
                        postImageViewModel.postVehicleImageData(requestVehicleMasterID, body)
                    } else {
                        Toasty.warning(
                            requireActivity(),
                            getString(R.string.no_internet_connection)
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (result.resultCode == AppCompatActivity.RESULT_CANCELED) {
            mBinding.vehicleImageProgress.visibility = GONE
            if (vehicleImageUrl.equals("NA", ignoreCase = true)) {
                mBinding.ivClickVehicleImage.visibility = VISIBLE
            } else {
                mBinding.ivClickVehicleImage.visibility = GONE
            }
        } else if (result.resultCode == AppCompatActivity.RESULT_FIRST_USER) {
            mBinding.vehicleImageProgress.visibility = GONE
            if (vehicleImageUrl.equals("NA", ignoreCase = true)) {
                mBinding.ivClickVehicleImage.visibility = VISIBLE
            } else {
                GONE.also { mBinding.ivClickVehicleImage.visibility = it }
            }
            Utils.showPermissionAlertDialog(requireActivity())
        }
    }


}