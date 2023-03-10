// Generated by view binder compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ymsli.com.cpo.R;

public final class DialogPermissionAlertBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView correctImage;

  @NonNull
  public final Button okButton;

  @NonNull
  public final TextView textViewSuccess;

  @NonNull
  public final TextView tvTitle;

  private DialogPermissionAlertBinding(@NonNull RelativeLayout rootView,
      @NonNull ImageView correctImage, @NonNull Button okButton, @NonNull TextView textViewSuccess,
      @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.correctImage = correctImage;
    this.okButton = okButton;
    this.textViewSuccess = textViewSuccess;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogPermissionAlertBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogPermissionAlertBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_permission_alert, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogPermissionAlertBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.correct_image;
      ImageView correctImage = ViewBindings.findChildViewById(rootView, id);
      if (correctImage == null) {
        break missingId;
      }

      id = R.id.ok_button;
      Button okButton = ViewBindings.findChildViewById(rootView, id);
      if (okButton == null) {
        break missingId;
      }

      id = R.id.textView_success;
      TextView textViewSuccess = ViewBindings.findChildViewById(rootView, id);
      if (textViewSuccess == null) {
        break missingId;
      }

      id = R.id.tvTitle;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new DialogPermissionAlertBinding((RelativeLayout) rootView, correctImage, okButton,
          textViewSuccess, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
