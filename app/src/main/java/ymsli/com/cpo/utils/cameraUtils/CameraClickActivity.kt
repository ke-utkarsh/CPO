package ymsli.com.cpo.utils.cameraUtils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.hardware.display.DisplayManager
import android.media.ExifInterface
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import android.util.Size
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ymsli.com.cpo.R

import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.robertlevonyan.demo.camerax.analyzer.LuminosityAnalyzer
import androidx.camera.core.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutionException
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import ymsli.com.cpo.databinding.ActivityCameraClickBinding
import ymsli.com.cpo.utils.Utils

class CameraClickActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraClickBinding
    private val displayManager by lazy { getSystemService(Context.DISPLAY_SERVICE) as DisplayManager }
    private var preview: Preview? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private var imageCapture: ImageCapture? = null
    private var imageAnalyzer: ImageAnalysis? = null
    private var lensFacing = CameraSelector.DEFAULT_BACK_CAMERA
    private var displayId = -1
    private lateinit var context: Context
    private lateinit var finalBitmap: Bitmap
    private var imageUri: Uri? = null
    private lateinit var outputDirectory: File
    private var camera: Camera? = null
    private var lightOn = false
    private var rotation = 0f
    private val permissions = mutableListOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
    )
    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value }) {
                onPermissionGranted()
            } else {
                setResult(Activity.RESULT_FIRST_USER, intent)
                finish()
            }
        }
    private val displayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) = Unit
        override fun onDisplayRemoved(displayId: Int) = Unit

        @RequiresApi(Build.VERSION_CODES.R)
        @SuppressLint("UnsafeExperimentalUsageError", "UnsafeOptInUsageError")
        override fun onDisplayChanged(displayId: Int) {
            if (displayId == this@CameraClickActivity.displayId) {
                display!!.rotation.also { preview?.targetRotation = it }
                imageCapture?.targetRotation = display!!.rotation
                imageAnalyzer?.targetRotation = display!!.rotation
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_camera_click)
        context = this@CameraClickActivity
        if (allPermissionsGranted()) {
            onPermissionGranted()
        } else {
            permissionRequest.launch(permissions.toTypedArray())
        }
    }

    private fun allPermissionsGranted() = permissions.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun onPermissionGranted() {
        binding.viewFinder.let { vf ->
            vf.post {
                displayId = vf.display.displayId
                startCamera()
            }
        }
        displayManager.registerDisplayListener(displayListener, null)
        binding.run {
            viewFinder.addOnAttachStateChangeListener(object :
                View.OnAttachStateChangeListener {
                override fun onViewDetachedFromWindow(v: View) =
                    displayManager.registerDisplayListener(displayListener, null)

                override fun onViewAttachedToWindow(v: View) =
                    displayManager.unregisterDisplayListener(displayListener)
            })
            outputDirectory = CommonKotlinMethods.getOutputDirectory(this@CameraClickActivity)
            btnTakePicture.setOnClickListener { captureImage() }
            btnSwitchCamera.setOnClickListener { toggleCamera() }
            btnBack.setOnClickListener {
                setResult(Activity.RESULT_CANCELED, intent)
                finish()
            }
            ivOk.setOnClickListener {
                intent.putExtra("IMAGE_ROTATION", rotation)
                intent.putExtra("IMAGE_URI", imageUri.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            btnFlash.setOnClickListener {
                val hasFlashUnit = camera?.cameraInfo?.hasFlashUnit()
                if (hasFlashUnit == true) {
                    lightOn = if (lightOn) {
                        camera?.cameraControl?.enableTorch(false)
                        btnFlash.setImageResource(R.drawable.ic_flash_off)
                        false;
                    } else {
                        camera?.cameraControl?.enableTorch(true)
                        btnFlash.setImageResource(R.drawable.ic_flash_on)
                        true
                    }
                }
            }
            binding.btnRetry.setOnClickListener {
                binding.llCameraPreview.visibility = View.VISIBLE
                binding.llCameraFinal.visibility = View.GONE
            }
        }
    }

    private fun captureImage() {
        val localImageCapture =
            imageCapture ?: throw IllegalStateException("Camera initialization failed.")
        ImageCapture.Metadata().apply {
            isReversedHorizontal = lensFacing == CameraSelector.DEFAULT_FRONT_CAMERA
        }
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val file: File = createFile(outputDirectory, "yyyy-MM-dd-HH-mm-ss-SSS", ".png")
        val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()
        localImageCapture.takePicture(
            outputOptions, // the options needed for the final image
            mainExecutor(), // the executor, on which the task will run
            object :
                ImageCapture.OnImageSavedCallback { // the callback, about the result of capture process
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    // This function is called if capture is successfully completed
                    outputFileResults.savedUri?.let { uri ->
                        Log.d(TAG, "Photo saved in $uri")
                        binding.llCameraPreview.visibility = View.GONE
                        binding.llCameraFinal.visibility = View.VISIBLE
                        val contentResolver: ContentResolver? = context.contentResolver
                        try {
                            val myBitmap = if (Build.VERSION.SDK_INT < 28) {
                                MediaStore.Images.Media.getBitmap(contentResolver, uri)
                            } else {
                                val source: ImageDecoder.Source =
                                    ImageDecoder.createSource(contentResolver!!, uri)
                                ImageDecoder.decodeBitmap(source)
                            }
                            imageUri = uri
                            try {
                                var ei: ExifInterface? = null
                                try {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                        ei = ExifInterface(file)
                                    }
                                    val orientation = ei?.getAttributeInt(
                                        ExifInterface.TAG_ORIENTATION,
                                        ExifInterface.ORIENTATION_UNDEFINED
                                    )
                                    var rotatedBitmap: Bitmap? = null
                                    when (orientation) {
                                        ExifInterface.ORIENTATION_ROTATE_90 -> {
                                            rotatedBitmap = Utils.rotateImage(myBitmap, 90f)
                                            rotation = 90f
                                        }
                                        ExifInterface.ORIENTATION_ROTATE_180 -> {
                                            rotatedBitmap = Utils.rotateImage(myBitmap, 180f)
                                            rotation = 180f
                                        }
                                        ExifInterface.ORIENTATION_ROTATE_270 -> {
                                            rotatedBitmap = Utils.rotateImage(myBitmap, 270f)
                                            rotation = 270f
                                        }
                                        ExifInterface.ORIENTATION_NORMAL -> {
                                            rotatedBitmap = myBitmap
                                            rotation = 0f
                                        }
                                        else -> {
                                            rotatedBitmap = myBitmap
                                            rotation = 0f
                                        }
                                    }
                                    finalBitmap = if (rotatedBitmap != null) {
                                        val nh =
                                            (rotatedBitmap.height * (1080.0 / rotatedBitmap.width)).toInt()
                                        Bitmap.createScaledBitmap(rotatedBitmap, 1080, nh, true)
                                    } else {
                                        myBitmap
                                    }
                                    val d: Drawable = BitmapDrawable(resources, myBitmap)
                                    binding.ivOverlay.setBackgroundDrawable(d)
                                } catch (error: OutOfMemoryError) {
                                    Toast.makeText(
                                        this@CameraClickActivity,
                                        "फ़ोटो लेते समय आपके पास पर्याप्त मेमोरी स्पेस नहीं है।",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                            imageUri = null
                        }
                    } ?: run {
                        Toast.makeText(context, "Unable to click image", Toast.LENGTH_LONG).show()
                        binding.llCameraPreview.visibility = View.VISIBLE
                        binding.llCameraFinal.visibility = View.GONE
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    val msg = "Photo capture failed: ${exception.message}"
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    Log.e(TAG, msg)
                    exception.printStackTrace()
                }
            }
        )
    }

    @SuppressLint("RestrictedApi")
    fun toggleCamera() = binding.btnSwitchCamera.toggleButton(
        flag = lensFacing == CameraSelector.DEFAULT_BACK_CAMERA,
        rotationAngle = 180f,
        firstIcon = R.drawable.ic_outline_camera_rear,
        secondIcon = R.drawable.ic_outline_camera_front,
    ) {
        lensFacing = if (it) {
            CameraSelector.DEFAULT_BACK_CAMERA
        } else {
            CameraSelector.DEFAULT_FRONT_CAMERA
        }
        startCamera()
    }

    private fun startCamera() {
        // This is the CameraX PreviewView where the camera will be rendered
        val viewFinder = binding.viewFinder
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            try {
                cameraProvider = cameraProviderFuture.get()
            } catch (e: InterruptedException) {
                Toast.makeText(context, "Error starting camera", Toast.LENGTH_SHORT).show()
                return@addListener
            } catch (e: ExecutionException) {
                Toast.makeText(context, "Error starting camera", Toast.LENGTH_SHORT).show()
                return@addListener
            }
            // The display information
            val metrics = DisplayMetrics().also { viewFinder.display.getRealMetrics(it) }
            // The ratio for the output image and preview
            val aspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels)
            // The display rotation
            val rotation = viewFinder.display.rotation
            val localCameraProvider = cameraProvider
                ?: throw IllegalStateException("Camera initialization failed.")
            // The Configuration of camera preview
            preview = Preview.Builder()
                .setTargetResolution(Size(1080, 1920)) // set the camera aspect ratio
                .setTargetRotation(rotation) // set the camera rotation
                .build()
            // The Configuration of image capture
            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY) //  When the capture mode is set to MIN_LATENCY, images may capture faster but the image quality may be reduced.
                .setFlashMode(ImageCapture.FLASH_MODE_OFF) // set capture flash
                .setTargetResolution(Size(1080, 1920)) // set the capture aspect ratio
                .setTargetRotation(rotation) // set the capture rotation
                .build()
            // The Configuration of image analyzing
            imageAnalyzer = ImageAnalysis.Builder()
                .setTargetResolution(Size(1080, 1920))// set the analyzer aspect ratio
                .setTargetRotation(rotation) // set the analyzer rotation
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST) // in our analysis, we care about the latest image
                .build()
                .also { setLuminosityAnalyzer(it) }
            // Unbind the use-cases before rebinding them
            localCameraProvider.unbindAll()
            // Bind all use cases to the camera with lifecycle
            bindToLifecycle(localCameraProvider, viewFinder)
        }, ContextCompat.getMainExecutor(context))
    }

    private fun aspectRatio(width: Int, height: Int): Int {
        val previewRatio = max(width, height).toDouble() / min(width, height)
        if (abs(previewRatio - RATIO_4_3_VALUE) <= abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3
        }
        return AspectRatio.RATIO_16_9
    }

    private fun setLuminosityAnalyzer(imageAnalysis: ImageAnalysis) {
        // Use a worker thread for image analysis to prevent glitches
        val analyzerThread = HandlerThread("LuminosityAnalysis").apply { start() }
        imageAnalysis.setAnalyzer(
            ThreadExecutor(Handler(analyzerThread.looper)),
            LuminosityAnalyzer()
        )
    }

    private fun bindToLifecycle(
        localCameraProvider: ProcessCameraProvider,
        viewFinder: PreviewView
    ) {
        try {
            camera = localCameraProvider.bindToLifecycle(
                this, // current lifecycle owner
                lensFacing, // either front or back facing
                preview, // camera preview use case
                imageCapture, // image capture use case
                imageAnalyzer, // image analyzer use case
            )
            // Attach the viewfinder's surface provider to preview use case
            preview?.setSurfaceProvider(viewFinder.surfaceProvider)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to bind use cases", e)
        }
    }

    companion object {
        private const val TAG = "CameraXDemo"
        private const val RATIO_4_3_VALUE = 4.0 / 3.0 // aspect ratio 4x3
        private const val RATIO_16_9_VALUE = 16.0 / 9.0 // aspect ratio 16x9
    }

    /**
     * Helper function used to create a timestamped file
     */
    private fun createFile(baseFolder: File, format: String, extension: String): File {
        return File(
            baseFolder, SimpleDateFormat(format, Locale.US)
                .format(System.currentTimeMillis()) + extension
        )
    }
}