package ymsli.com.cpo.utils

import android.app.Activity
import android.app.Dialog
import android.app.NotificationManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.signature.ObjectKey
import ymsli.com.cpo.R
import ymsli.com.cpo.ui.view.activity.LoginActivity
import ymsli.com.cpo.utils.cameraUtils.CommonKotlinMethods
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


object Utils {
    @JvmStatic
    fun logThis(title: String?, msg: String?) {
        if (Constants.DEBUGGABLE) {
            Log.e(title, msg!!)
        }
    }

    //  ==================================== Get Site Survey Id ==================================-->
    private const val ALLOWED_CHARACTERS = "0123456789"
    fun getSiteSurveyId(): String {
        val saveTime = SimpleDateFormat("yyMMddHHmmssSS", Locale.ENGLISH).format(Date())
        val random = Random()
        val sb = StringBuilder(3)
        for (i in 0..2) sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        return saveTime
    }

    //  ==================================== TransactionN umber ==================================-->
    fun getTransactionNumber(): String {
        val saveTime = SimpleDateFormat("ddHHmmssSS", Locale.ENGLISH).format(Date())
        val random = Random()
        val sb = StringBuilder(3)
        for (i in 0..2) sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        return saveTime + sb
    }

    //  ==================================== Hide Soft KeyBoard ==================================-->
    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideSoftKeyBoardWithoutView(activity: Activity) {
        try {
            val inputMethodManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    //  ====================================== Rotate Image ======================================-->
    fun rotateImage(source: Bitmap, angle: Float): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height,
            matrix, true
        )
    }

    //  ===================================== Session TimeOut ====================================-->
   /* fun sessionTimeOutStartLoginScreen(context: Context) {
        GlobalScope.launch {
            val preferences: SharedPreferences = context.getSharedPreferences(
                SharedPreferenceConstant.PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
            delay(1000)
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }*/

    //  ====================================== Random Number =====================================-->
    // private const val ALLOWED_CHARACTERS = "0123456789"
    fun getRandomNumber(): Int {
        val random = Random()
        val sb = StringBuilder(3)
        for (i in 0..2) sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        return Integer.parseInt(sb.toString())
    }

    //  ==================================== Notification Enabled ================================-->
    fun getFileDataFromDrawable(bitmap: Bitmap): ByteArray? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }

    //  ==================================== Notification Enabled ================================-->
    fun isNotificationEnabled(context: Context): Boolean {
        return NotificationManagerCompat.from(context).areNotificationsEnabled()
    }

    //  ======================================= Session TimeOut ==================================-->
    fun sessionTimeOutStartLoginScreen(activity: Activity) {
        try {
            clearSharedPreference(activity)
            val `in` = Intent(activity, LoginActivity::class.java)
            activity.startActivity(`in`)
            activity.finishAffinity()
        } catch (ex: Exception) {
            val `in` = Intent(activity, LoginActivity::class.java)
            activity.startActivity(`in`)
            activity.finishAffinity()
            logThis("Logout", "Exception in session logout ${ex.localizedMessage}")
        }
    }

    //  ==================================== Clear SharedPreference ==============================-->
    private fun clearSharedPreference(activity: Activity) {
        val preferences = activity.getSharedPreferences(
            SharedPreferenceConstant.PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }

    //  ===================================== Clear Notifications ================================-->
    fun clearNotifications(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }

    fun setImage(context: Context?, sUserImage: String, iv_userImage: ImageView?) {
        try {
            if (context != null) {
                if (iv_userImage != null) {
                    Glide.with(context)
                        .load(sUserImage)
                        .apply(RequestOptions.centerInsideTransform())
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .error(R.drawable.error_photo)
                        .signature(
                            ObjectKey(
                                sUserImage + System.currentTimeMillis().toString()
                            )
                        )
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(object : SimpleTarget<Drawable?>() {
                            override fun onResourceReady(
                                resource: Drawable,
                                @Nullable transition: Transition<in Drawable?>?
                            ) {
                                iv_userImage.setBackgroundDrawable(resource)

                            }
                        })
                }
            }



        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    /*  public static void showToast(final Activity activity, final String msg) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                LayoutInflater inflater = activity.getLayoutInflater();
                View toastLayout = inflater.inflate(R.layout.layout_common_toast,
                        (ViewGroup) activity.findViewById(R.id.toast_root_view));
                TextView body = (TextView) toastLayout.findViewById(R.id.tvToastMsg);
                body.setText(msg);
                Toast toast = new Toast(activity);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(toastLayout);
                toast.show();
            }
        });
    }

    public static void showLongToast(final Activity activity, final String msg) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                LayoutInflater inflater = activity.getLayoutInflater();
                View toastLayout = inflater.inflate(R.layout.layout_common_toast,
                        (ViewGroup) activity.findViewById(R.id.toast_root_view));
                TextView body = (TextView) toastLayout.findViewById(R.id.tvToastMsg);
                body.setText(msg);
                Toast toast = new Toast(activity);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(toastLayout);
                toast.show();
            }
        });
    }*/
    fun showPermissionAlertDialog(activity: Activity) {
        if (!activity.isFinishing) {
            val permissionAlertDialog = Dialog(activity)
            permissionAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            permissionAlertDialog.setContentView(R.layout.dialog_permission_alert)
            permissionAlertDialog.setCancelable(false)
            permissionAlertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val ok_button = permissionAlertDialog.findViewById<Button>(R.id.ok_button)
            ok_button.setOnClickListener {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.data = Uri.parse("package:" + activity.packageName)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                activity.startActivity(intent)
                permissionAlertDialog.dismiss()
            }
            try {
                permissionAlertDialog.show()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

}