// Generated by view binder compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ymsli.com.cpo.R;

public final class ActivityQrScanBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout constraintLayout3;

  @NonNull
  public final ImageView ivBack;

  @NonNull
  public final ProgressBar pbProgress;

  @NonNull
  public final SurfaceView surfaceView;

  @NonNull
  public final TextView tvNftHead;

  @NonNull
  public final TextView tvScanAgain;

  @NonNull
  public final TextView txtBarcodeValue;

  private ActivityQrScanBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout constraintLayout3, @NonNull ImageView ivBack,
      @NonNull ProgressBar pbProgress, @NonNull SurfaceView surfaceView,
      @NonNull TextView tvNftHead, @NonNull TextView tvScanAgain,
      @NonNull TextView txtBarcodeValue) {
    this.rootView = rootView;
    this.constraintLayout3 = constraintLayout3;
    this.ivBack = ivBack;
    this.pbProgress = pbProgress;
    this.surfaceView = surfaceView;
    this.tvNftHead = tvNftHead;
    this.tvScanAgain = tvScanAgain;
    this.txtBarcodeValue = txtBarcodeValue;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityQrScanBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityQrScanBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_qr_scan, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityQrScanBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.constraintLayout3;
      ConstraintLayout constraintLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout3 == null) {
        break missingId;
      }

      id = R.id.ivBack;
      ImageView ivBack = ViewBindings.findChildViewById(rootView, id);
      if (ivBack == null) {
        break missingId;
      }

      id = R.id.pbProgress;
      ProgressBar pbProgress = ViewBindings.findChildViewById(rootView, id);
      if (pbProgress == null) {
        break missingId;
      }

      id = R.id.surfaceView;
      SurfaceView surfaceView = ViewBindings.findChildViewById(rootView, id);
      if (surfaceView == null) {
        break missingId;
      }

      id = R.id.tvNftHead;
      TextView tvNftHead = ViewBindings.findChildViewById(rootView, id);
      if (tvNftHead == null) {
        break missingId;
      }

      id = R.id.tvScanAgain;
      TextView tvScanAgain = ViewBindings.findChildViewById(rootView, id);
      if (tvScanAgain == null) {
        break missingId;
      }

      id = R.id.txtBarcodeValue;
      TextView txtBarcodeValue = ViewBindings.findChildViewById(rootView, id);
      if (txtBarcodeValue == null) {
        break missingId;
      }

      return new ActivityQrScanBinding((ConstraintLayout) rootView, constraintLayout3, ivBack,
          pbProgress, surfaceView, tvNftHead, tvScanAgain, txtBarcodeValue);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
