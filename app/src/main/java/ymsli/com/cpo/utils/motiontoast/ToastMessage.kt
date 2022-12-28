package ymsli.com.cpo.utils.motiontoast

import android.app.Activity
import androidx.core.content.res.ResourcesCompat

import com.ymsli.cotb.utils.motiontoast.MotionToast
import com.ymsli.cotb.utils.motiontoast.MotionToastStyle
import ymsli.com.cpo.R

/**
 * @Author: Shivani
 * @Created By: VE00YM491
 * @Date: 15-11-2022
 * @Description:
 */
object ToastMessage {
    fun success(activity: Activity,title:String, message: String)
    {

        MotionToast.createColorToast(activity,title,message,MotionToastStyle.SUCCESS,
            MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,ResourcesCompat.getFont(activity, R.font.poppins_regular))
    }
    fun error(activity:Activity,title:String, message: String)
    {
        MotionToast.createColorToast(activity,title,message,MotionToastStyle.ERROR,
            MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,ResourcesCompat.getFont(activity,R.font.poppins_regular))

    }
    fun warning(activity:Activity, title:String, message: String)
    {
        MotionToast.createColorToast(activity,title,message,MotionToastStyle.WARNING,
            MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,ResourcesCompat.getFont(activity,R.font.poppins_regular))
    }

    fun info(activity:Activity, title:String, message: String)
    {
        MotionToast.createColorToast(activity,title,message,MotionToastStyle.INFO,
            MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,ResourcesCompat.getFont(activity,R.font.poppins_regular))
    }
    fun delete(activity:Activity, title:String, message: String)
    {
        MotionToast.createColorToast(activity,title,message,MotionToastStyle.DELETE,
            MotionToast.GRAVITY_BOTTOM,MotionToast.LONG_DURATION,ResourcesCompat.getFont(activity,R.font.poppins_regular))
    }

}