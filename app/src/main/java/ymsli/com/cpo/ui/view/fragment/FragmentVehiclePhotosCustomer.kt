package ymsli.com.cpo.ui.view.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
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
import ymsli.com.cpo.R
import ymsli.com.cpo.data.model.PdiImage
import ymsli.com.cpo.data.model.VehiclePhoto
import ymsli.com.cpo.databinding.FragmentVehicalPhotosBinding
import ymsli.com.cpo.databinding.FragmentVehiclePhotosCustomerBinding
import ymsli.com.cpo.ui.view.activity.WebViewActivity
import ymsli.com.cpo.ui.view.adapter.PdiImagesAdapter
import ymsli.com.cpo.ui.view.adapter.VehicleImagesAdapter
import ymsli.com.cpo.ui.viewModel.VehicleInformationViewModel
import ymsli.com.cpo.utils.CancelableToast
import ymsli.com.cpo.utils.Constants
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.Utils
import ymsli.com.cpo.utils.cameraUtils.CameraClickActivity

class FragmentVehiclePhotosCustomer: Fragment() {
    private val vm: VehicleInformationViewModel by activityViewModels()
    private lateinit var pdiImageAdapter: PdiImagesAdapter
    private lateinit var vehicleImageAdapter: VehicleImagesAdapter
    private var pdiImageList: ArrayList<PdiImage> = ArrayList()
    private var vehiclePhotoList: ArrayList<VehiclePhoto> = ArrayList()
    private lateinit var mBinding: FragmentVehiclePhotosCustomerBinding
    lateinit var vehicleMasterId: String
    private lateinit var dialog: Dialog
    private var nft:Boolean=true;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentVehiclePhotosCustomerBinding.inflate(inflater, container, false)

        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getObserveData()
        clickListener()
    }

    private fun init() {
        pdiImageAdapter = PdiImagesAdapter(requireContext(), pdiImageList, nft){
            if(it[1]==0){
                pdiImageList.removeAt(it[0])
                pdiImageAdapter.notifyDataSetChanged()
            }else{
                showImageDialog("PDI",pdiImageList.get(it[0]).OriginalImageUrl,pdiImageList[it[0]].IpfsUrl)
            }
        }
        mBinding.rvPdiImages.adapter = pdiImageAdapter

        vehicleImageAdapter = VehicleImagesAdapter(requireContext(), vehiclePhotoList, nft){
            if(it[1]==0){
                vehiclePhotoList.removeAt(it[0])
                vehicleImageAdapter.notifyDataSetChanged()
            }else{
                showImageDialog("Vehicle Photo",vehiclePhotoList.get(it[0]).OriginalImageUrl, vehiclePhotoList[it[0]].IpfsUrl)
            }
        }
        mBinding.rvVehicleImages.adapter = vehicleImageAdapter
    }

    private fun showImageDialog(s: String, originalImageUrl: String, ipfsUrl: String) {
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
        dialog.findViewById<TextView?>(R.id.tvTitle).text = s
        val close:ImageView=dialog.findViewById(R.id.ivClose)
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

    }

    private fun showHideImage() {
        if (pdiImageList.size == 0) {
            mBinding.rvPdiImages.visibility = View.GONE
        } else {
            mBinding.rvPdiImages.visibility = View.VISIBLE
        }

        if (vehiclePhotoList.size == 0) {
            mBinding.rvVehicleImages.visibility = View.GONE
        } else {
            mBinding.rvVehicleImages.visibility = View.VISIBLE
        }
    }


    /**
     * Here posted image data is observed
     * */
    private fun getObserveData() {
        vm.vehicleInformationResponse.observe(viewLifecycleOwner, Observer { event ->
            event.peekContent().let { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { vehicleInformation ->
                            if(vehicleInformation.Result.NFTStatus.Id==3){
                                nft=true
                                init()
                            }
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

    }



}