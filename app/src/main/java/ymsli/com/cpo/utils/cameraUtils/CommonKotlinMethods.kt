package ymsli.com.cpo.utils.cameraUtils;

import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import ymsli.com.cpo.R
import java.io.File

class CommonKotlinMethods {
    companion object temp {
        /** Use external media if it is available, our app's file directory otherwise */
        @JvmStatic
        fun getOutputDirectory(context: Context): File {
            val appContext = context.applicationContext
            appContext.resources.getString(R.string.app_name)
            return appContext.filesDir
        }
    }
}