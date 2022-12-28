package ymsli.com.cpo.utils

import android.app.Activity
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import ymsli.com.cpo.R
import ymsli.com.cpo.databinding.CancellableToastBinding

class CancelableToast(
    private val activity:Activity,
    private val msg:String,private val isCancelable:Boolean = true){

    private lateinit var mWindow:Window
    private lateinit var mBinding:CancellableToastBinding

    fun success() {
        val bottomSheet = BottomSheetDialog(activity, R.style.BottomSheetDialogTheme)
        bottomSheet.setContentView(R.layout.cancellable_toast)
        bottomSheet.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        bottomSheet.findViewById<ImageView>(R.id.ivImage)
            ?.setBackgroundResource(R.drawable.ic_baseline_check_circle_24);
        bottomSheet.findViewById<ConstraintLayout>(R.id.clToast)
            ?.setBackgroundResource(R.drawable.cancellable_toast_success)
        bottomSheet.findViewById<TextView>(R.id.tvHeading)?.text = "Success"
        bottomSheet.findViewById<TextView>(R.id.tvMsg)?.text = msg
        (bottomSheet.findViewById(R.id.btnCancel) as AppCompatButton?)?.setOnClickListener {
            activity.finish()
            bottomSheet.dismiss()
        }
        bottomSheet.setCanceledOnTouchOutside(isCancelable)
        bottomSheet.show()
    }
    fun error() {
        var bottomSheet = BottomSheetDialog(activity, R.style.BottomSheetDialogTheme)
        bottomSheet.setContentView(R.layout.cancellable_toast)
        bottomSheet.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        bottomSheet.findViewById<TextView>(R.id.tvHeading)?.text = "Error!!"
        bottomSheet.findViewById<TextView>(R.id.tvMsg)?.text = msg
        (bottomSheet.findViewById(R.id.btnCancel) as AppCompatButton?)?.setOnClickListener {
            bottomSheet.dismiss()
        }
        bottomSheet.setCanceledOnTouchOutside(isCancelable)
        bottomSheet.show()

    }
    fun alert() {
        var bottomSheet = BottomSheetDialog(activity, R.style.BottomSheetDialogTheme)
        bottomSheet.setContentView(R.layout.cancellable_toast)
        bottomSheet.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        bottomSheet.findViewById<ImageView>(R.id.ivImage)
            ?.setBackgroundResource(R.drawable.ic_baseline_warning_24)
        bottomSheet.findViewById<ConstraintLayout>(R.id.clToast)
            ?.setBackgroundResource(R.drawable.cancellable_toast_alert)
        bottomSheet.findViewById<TextView>(R.id.tvHeading)?.text = "Alert!!"
        bottomSheet.findViewById<TextView>(R.id.tvMsg)?.text = msg
        (bottomSheet.findViewById(R.id.btnCancel) as AppCompatButton?)?.setOnClickListener {
            bottomSheet.dismiss()
        }
        bottomSheet.setCanceledOnTouchOutside(isCancelable)
        bottomSheet.show()
    }
}