// Generated by view binder compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ymsli.com.cpo.R;

public final class ActivityServiceItemsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnNext;

  @NonNull
  public final ConstraintLayout clToolbar;

  @NonNull
  public final ImageView ivBack;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RecyclerView rvServiceItem;

  @NonNull
  public final TextView tvNoData;

  @NonNull
  public final TextView tvPageHead;

  private ActivityServiceItemsBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnNext,
      @NonNull ConstraintLayout clToolbar, @NonNull ImageView ivBack,
      @NonNull ProgressBar progressBar, @NonNull RecyclerView rvServiceItem,
      @NonNull TextView tvNoData, @NonNull TextView tvPageHead) {
    this.rootView = rootView;
    this.btnNext = btnNext;
    this.clToolbar = clToolbar;
    this.ivBack = ivBack;
    this.progressBar = progressBar;
    this.rvServiceItem = rvServiceItem;
    this.tvNoData = tvNoData;
    this.tvPageHead = tvPageHead;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityServiceItemsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityServiceItemsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_service_items, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityServiceItemsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnNext;
      Button btnNext = ViewBindings.findChildViewById(rootView, id);
      if (btnNext == null) {
        break missingId;
      }

      id = R.id.clToolbar;
      ConstraintLayout clToolbar = ViewBindings.findChildViewById(rootView, id);
      if (clToolbar == null) {
        break missingId;
      }

      id = R.id.ivBack;
      ImageView ivBack = ViewBindings.findChildViewById(rootView, id);
      if (ivBack == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.rvServiceItem;
      RecyclerView rvServiceItem = ViewBindings.findChildViewById(rootView, id);
      if (rvServiceItem == null) {
        break missingId;
      }

      id = R.id.tvNoData;
      TextView tvNoData = ViewBindings.findChildViewById(rootView, id);
      if (tvNoData == null) {
        break missingId;
      }

      id = R.id.tvPageHead;
      TextView tvPageHead = ViewBindings.findChildViewById(rootView, id);
      if (tvPageHead == null) {
        break missingId;
      }

      return new ActivityServiceItemsBinding((ConstraintLayout) rootView, btnNext, clToolbar,
          ivBack, progressBar, rvServiceItem, tvNoData, tvPageHead);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
