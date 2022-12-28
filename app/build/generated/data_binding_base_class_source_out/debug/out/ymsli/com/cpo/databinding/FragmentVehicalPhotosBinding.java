// Generated by view binder compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ymsli.com.cpo.R;

public final class FragmentVehicalPhotosBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnSubmit;

  @NonNull
  public final ImageView ivClickPdiImage;

  @NonNull
  public final ImageView ivClickVehicleImage;

  @NonNull
  public final ProgressBar pdiImageProgress;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RecyclerView rvPdiImages;

  @NonNull
  public final RecyclerView rvVehicleImages;

  @NonNull
  public final ProgressBar vehicleImageProgress;

  private FragmentVehicalPhotosBinding(@NonNull LinearLayout rootView, @NonNull Button btnSubmit,
      @NonNull ImageView ivClickPdiImage, @NonNull ImageView ivClickVehicleImage,
      @NonNull ProgressBar pdiImageProgress, @NonNull ProgressBar progressBar,
      @NonNull RecyclerView rvPdiImages, @NonNull RecyclerView rvVehicleImages,
      @NonNull ProgressBar vehicleImageProgress) {
    this.rootView = rootView;
    this.btnSubmit = btnSubmit;
    this.ivClickPdiImage = ivClickPdiImage;
    this.ivClickVehicleImage = ivClickVehicleImage;
    this.pdiImageProgress = pdiImageProgress;
    this.progressBar = progressBar;
    this.rvPdiImages = rvPdiImages;
    this.rvVehicleImages = rvVehicleImages;
    this.vehicleImageProgress = vehicleImageProgress;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentVehicalPhotosBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentVehicalPhotosBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_vehical_photos, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentVehicalPhotosBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSubmit;
      Button btnSubmit = ViewBindings.findChildViewById(rootView, id);
      if (btnSubmit == null) {
        break missingId;
      }

      id = R.id.ivClickPdiImage;
      ImageView ivClickPdiImage = ViewBindings.findChildViewById(rootView, id);
      if (ivClickPdiImage == null) {
        break missingId;
      }

      id = R.id.ivClickVehicleImage;
      ImageView ivClickVehicleImage = ViewBindings.findChildViewById(rootView, id);
      if (ivClickVehicleImage == null) {
        break missingId;
      }

      id = R.id.pdiImageProgress;
      ProgressBar pdiImageProgress = ViewBindings.findChildViewById(rootView, id);
      if (pdiImageProgress == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.rvPdiImages;
      RecyclerView rvPdiImages = ViewBindings.findChildViewById(rootView, id);
      if (rvPdiImages == null) {
        break missingId;
      }

      id = R.id.rvVehicleImages;
      RecyclerView rvVehicleImages = ViewBindings.findChildViewById(rootView, id);
      if (rvVehicleImages == null) {
        break missingId;
      }

      id = R.id.vehicleImageProgress;
      ProgressBar vehicleImageProgress = ViewBindings.findChildViewById(rootView, id);
      if (vehicleImageProgress == null) {
        break missingId;
      }

      return new FragmentVehicalPhotosBinding((LinearLayout) rootView, btnSubmit, ivClickPdiImage,
          ivClickVehicleImage, pdiImageProgress, progressBar, rvPdiImages, rvVehicleImages,
          vehicleImageProgress);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
