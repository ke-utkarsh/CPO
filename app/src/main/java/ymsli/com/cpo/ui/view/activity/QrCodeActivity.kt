package ymsli.com.cpo.ui.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Display
import android.view.WindowManager
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import es.dmoral.toasty.BuildConfig
import es.dmoral.toasty.Toasty
import ymsli.com.cpo.databinding.ActivityQrCodeBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class QrCodeActivity : AppCompatActivity() {
    private lateinit var vehicleId:String
    private val tag = "QR Code"
    lateinit var binding: ActivityQrCodeBinding
    private var activity: Activity = this@QrCodeActivity
    lateinit var bitmap: Bitmap
    lateinit var qrEncoder: QRGEncoder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        initView()
        clickListener()
    }

    private fun initView() {
        if(intent.getStringExtra("vehicleId")!=null) {
            vehicleId = intent.getStringExtra("vehicleId").toString()


            binding.tvVehicleId.text= "Vehicle Id: $vehicleId"
            val windowManager: WindowManager = getSystemService(WINDOW_SERVICE) as WindowManager
            val display: Display = windowManager.defaultDisplay
            val point: Point = Point()
            display.getSize(point)
            val width = point.x
            val height = point.y
            var dimen = if (width < height) width else height
            dimen = dimen * 3 / 4
            qrEncoder = QRGEncoder(vehicleId, null, QRGContents.Type.TEXT, dimen)
            try {
                bitmap = qrEncoder.getBitmap(2)
                binding.ivQrCode.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
                Toasty.error(activity,"Sorry we are unable to generate QR code currently, please try again later").show()
            }
        }
    }

    private fun clickListener() {
        binding.btnClose.setOnClickListener {
            finish()
        }
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.ivShare.setOnClickListener {
            if(bitmap!=null){
                var uri: Uri?=saveImage(bitmap)
                if(uri!=null){
                    shareImageUri(uri)
                }
            }

        }
    }

    private fun shareImageUri(uri: Uri) {
        var intent= Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.type = "image/png";
        startActivity(intent)
    }

    private fun saveImage(image:Bitmap) : Uri? {
        var imagesFolder: File = File(cacheDir,"images")
        var uri: Uri?=null
        try {
            imagesFolder.mkdirs()
            val file = File(imagesFolder, "shared_image.png")
            val stream = FileOutputStream(file)
            image.compress(Bitmap.CompressFormat.PNG, 90, stream)
            stream.flush()
            stream.close()
            uri = FileProvider.getUriForFile(activity,"ymsli.com.cpo.provider",file)
        } catch (e: IOException) {
            Log.d("QRShare", "IOException while trying to write file for sharing: " + e.message)
        }
        return uri
    }
}