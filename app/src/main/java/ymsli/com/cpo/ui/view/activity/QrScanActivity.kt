package ymsli.com.cpo.ui.view.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.SurfaceHolder
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_qr_scan.*
import ymsli.com.cpo.R
import ymsli.com.cpo.data.api.RequestBodies
import ymsli.com.cpo.data.model.VehicleInformationData
import ymsli.com.cpo.databinding.ActivityQrScanBinding
import ymsli.com.cpo.ui.viewModel.QrScanViewModel
import ymsli.com.cpo.utils.CancelableToast
import ymsli.com.cpo.utils.Resource
import ymsli.com.cpo.utils.Utils
import java.io.IOException

@AndroidEntryPoint
class QrScanActivity : AppCompatActivity() {
    lateinit var binding: ActivityQrScanBinding
    private var activity: Activity= this@QrScanActivity
    private val REQUEST_CAMERA_PERMISSION = 201
    private lateinit var barcodeDetector: BarcodeDetector
    private lateinit var cameraSource: CameraSource
    var intentData = ""
    var isEmail = false
    private val vm: QrScanViewModel by viewModels()
    private lateinit var vehicleInformationData : VehicleInformationData
    private var isScanned: Boolean=false
    private lateinit var bottomSheet: BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQrScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_qr_scan)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        bottomSheet= BottomSheetDialog(activity)
        responseObserver()
        if(!checkPermission()){
            requestPermission()
        }
        if(checkPermission()){
            initialiseDetectorsAndSources()
        }
        bottomSheet.setOnCancelListener {
            isScanned=false
        }
        binding.tvScanAgain.setOnClickListener {
            bottomSheet.dismiss()
            isScanned=true
        }
        binding.ivBack.setOnClickListener {
            var intent=Intent(activity,CustomerNftDetailActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

    private fun initialiseDetectorsAndSources() {
        //Toast.makeText(applicationContext, "Barcode scanner started", Toast.LENGTH_SHORT).show()
        barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.QR_CODE)
            .build()
        cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true) //you should add this feature
            .build()
        surfaceView.getHolder().addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(this@QrScanActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource.start(surfaceView.getHolder())
                    } else {
                        ActivityCompat.requestPermissions(this@QrScanActivity, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })
        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                //Toast.makeText(applicationContext, "To prevent memory leaks qr code scanner has been stopped", Toast.LENGTH_SHORT).show()
            }

            override fun receiveDetections(detections: Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() != 0 && !isScanned) {
                    txtBarcodeValue.post(Runnable {
                        if (barcodes.valueAt(0).email != null) {
                            txtBarcodeValue.removeCallbacks(null)
                            intentData = barcodes.valueAt(0).email.address
                            txtBarcodeValue.setText("QR Code Detected")
                            isEmail = true
                        } else {
                            isEmail = false
                            intentData = barcodes.valueAt(0).displayValue
                            txtBarcodeValue.setText("QR Code Detected")
                            vm.vehicleInformationRequest(RequestBodies.VehicleInformationBody(intentData))
                            isScanned=true
                        }
                    })
                }else if(barcodes.size() != 0){
                    txtBarcodeValue.setText("No QR detected")
                }
            }
        })
    }

    private fun responseObserver() {
        vm.vehicleInformationResponse.observe(this, Observer { event->
            event.getContentIfNotHandled()?.let { response->
                when(response){
                    is Resource.Success->{
                        binding.pbProgress.visibility=GONE
                        response.data?.let {
                            Log.e("VR", "Vehicle Information Response  is :   ${it.Result}")
                            vehicleInformationData = it.Result
                            scannedBottomSheet(vehicleInformationData)

                        }
                    }is Resource.Error -> {
                    binding.pbProgress.visibility=GONE
                    response.message?.let { message ->
                        Log.e("VR", "Error message is : $message")
                        if (message == "Unauthorized") {
                            CancelableToast(activity, resources.getString(R.string.unauthorized_error)).alert()
                            Utils.sessionTimeOutStartLoginScreen(activity)
                            initialiseDetectorsAndSources()
                        } else {
                            CancelableToast(activity,message).error()
                            initialiseDetectorsAndSources()
                        }
                        Handler(Looper.getMainLooper()).postDelayed(Runnable {
                            isScanned=false
                        },10000)

                    }
                }
                    is Resource.Loading -> {
                        binding.pbProgress.visibility= VISIBLE
                    }
                }
            }
        })
    }

    private fun scannedBottomSheet(vehicleInformationData: VehicleInformationData) {
        bottomSheet.setContentView(R.layout.bottomsheet_scanned_qr)

        bottomSheet.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        bottomSheet.findViewById<TextView>(R.id.tvOwnerBS)?.text= vehicleInformationData.CustomerName.toString()
        bottomSheet.findViewById<TextView>(R.id.tvColorBS)?.text=vehicleInformationData.Colour
        bottomSheet.findViewById<TextView>(R.id.tvModelBS)?.text=vehicleInformationData.Year
        bottomSheet.findViewById<TextView>(R.id.tvVinBS)?.text=vehicleInformationData.VIN
        if(vehicleInformationData.TripMeterReading==null){
            bottomSheet.findViewById<TextView>(R.id.tvReadingBS)?.text="N.A."
        }else{
            bottomSheet.findViewById<TextView>(R.id.tvReadingBS)?.text=vehicleInformationData.TripMeterReading.toString()
        }
        bottomSheet.findViewById<TextView>(R.id.tvNameHeadBS)?.text="Yamaha "+vehicleInformationData.Model+" M-"
        displayImage(vehicleInformationData.DisplayImage1,bottomSheet)
        bottomSheet.findViewById<ImageView>(R.id.ivNextBS)?.setOnClickListener {
            var intent= Intent(activity,VehicleInformationCustomer::class.java)
            intent.putExtra("vehicleId",vehicleInformationData.VehicleId)
            intent.putExtra("NFT_TYPE","NFT")
            intent.putExtra("viewOnly","y")
            startActivity(intent)
        }
        bottomSheet.setCanceledOnTouchOutside(false)
        bottomSheet.show()
    }

    private fun displayImage(displayImage: String, bottomSheet: BottomSheetDialog) {
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
                    bottomSheet.findViewById<ProgressBar>(R.id.pbProgressBS)?.visibility = View.GONE
                    return false;
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    bottomSheet.findViewById<ProgressBar>(R.id.pbProgressBS)?.visibility = View.GONE
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
                    bottomSheet.findViewById<ImageView>(R.id.ivVehiclePhotoBS)?.setBackgroundDrawable(resource)
                }
            })
    }

    private fun checkPermission(): Boolean {
        // checking of permissions.
        val permission1 = ContextCompat.checkSelfPermission(applicationContext,
            Manifest.permission.CAMERA
        )

        return permission1 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, arrayOf(
            Manifest.permission.CAMERA,
        ), REQUEST_CAMERA_PERMISSION)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.size > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                val cameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED

                if (cameraPermission) {
                    //Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
    override fun onPause() {
        super.onPause()
        cameraSource.release()
    }

    override fun onResume() {
        super.onResume()
        initialiseDetectorsAndSources()
    }
}