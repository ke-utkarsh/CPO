// Generated by view binder compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

public final class DialogConnectBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnConnect;

  @NonNull
  public final ImageView ivClose1;

  @NonNull
  public final ImageView ivWalletConnect;

  @NonNull
  public final TextView tvPlease;

  private DialogConnectBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnConnect,
      @NonNull ImageView ivClose1, @NonNull ImageView ivWalletConnect, @NonNull TextView tvPlease) {
    this.rootView = rootView;
    this.btnConnect = btnConnect;
    this.ivClose1 = ivClose1;
    this.ivWalletConnect = ivWalletConnect;
    this.tvPlease = tvPlease;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogConnectBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogConnectBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_connect, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogConnectBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnConnect;
      Button btnConnect = ViewBindings.findChildViewById(rootView, id);
      if (btnConnect == null) {
        break missingId;
      }

      id = R.id.ivClose1;
      ImageView ivClose1 = ViewBindings.findChildViewById(rootView, id);
      if (ivClose1 == null) {
        break missingId;
      }

      id = R.id.ivWalletConnect;
      ImageView ivWalletConnect = ViewBindings.findChildViewById(rootView, id);
      if (ivWalletConnect == null) {
        break missingId;
      }

      id = R.id.tvPlease;
      TextView tvPlease = ViewBindings.findChildViewById(rootView, id);
      if (tvPlease == null) {
        break missingId;
      }

      return new DialogConnectBinding((ConstraintLayout) rootView, btnConnect, ivClose1,
          ivWalletConnect, tvPlease);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}