// Generated by view binder compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ymsli.com.cpo.R;

public final class TempBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView ivChildBottomLine;

  @NonNull
  public final ImageView ivChildBottomLineTwo;

  @NonNull
  public final ImageView ivChildBusStand;

  @NonNull
  public final ImageView ivInitialChildBusStand;

  @NonNull
  public final ImageView ivTopLine;

  @NonNull
  public final LinearLayout llSecond;

  @NonNull
  public final TextView tvChangeTheBus;

  @NonNull
  public final TextView tvChildBusNme;

  @NonNull
  public final TextView tvChildInternalTime;

  @NonNull
  public final TextView tvChildRouteName;

  @NonNull
  public final TextView tvChildTakeBus;

  @NonNull
  public final TextView tvReachedTime;

  @NonNull
  public final TextView tvScheduleTime;

  @NonNull
  public final TextView tvTextReachedTime;

  @NonNull
  public final TextView tvTextScheduleTime;

  private TempBinding(@NonNull LinearLayout rootView, @NonNull ImageView ivChildBottomLine,
      @NonNull ImageView ivChildBottomLineTwo, @NonNull ImageView ivChildBusStand,
      @NonNull ImageView ivInitialChildBusStand, @NonNull ImageView ivTopLine,
      @NonNull LinearLayout llSecond, @NonNull TextView tvChangeTheBus,
      @NonNull TextView tvChildBusNme, @NonNull TextView tvChildInternalTime,
      @NonNull TextView tvChildRouteName, @NonNull TextView tvChildTakeBus,
      @NonNull TextView tvReachedTime, @NonNull TextView tvScheduleTime,
      @NonNull TextView tvTextReachedTime, @NonNull TextView tvTextScheduleTime) {
    this.rootView = rootView;
    this.ivChildBottomLine = ivChildBottomLine;
    this.ivChildBottomLineTwo = ivChildBottomLineTwo;
    this.ivChildBusStand = ivChildBusStand;
    this.ivInitialChildBusStand = ivInitialChildBusStand;
    this.ivTopLine = ivTopLine;
    this.llSecond = llSecond;
    this.tvChangeTheBus = tvChangeTheBus;
    this.tvChildBusNme = tvChildBusNme;
    this.tvChildInternalTime = tvChildInternalTime;
    this.tvChildRouteName = tvChildRouteName;
    this.tvChildTakeBus = tvChildTakeBus;
    this.tvReachedTime = tvReachedTime;
    this.tvScheduleTime = tvScheduleTime;
    this.tvTextReachedTime = tvTextReachedTime;
    this.tvTextScheduleTime = tvTextScheduleTime;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static TempBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static TempBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.temp, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static TempBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ivChildBottomLine;
      ImageView ivChildBottomLine = ViewBindings.findChildViewById(rootView, id);
      if (ivChildBottomLine == null) {
        break missingId;
      }

      id = R.id.ivChildBottomLineTwo;
      ImageView ivChildBottomLineTwo = ViewBindings.findChildViewById(rootView, id);
      if (ivChildBottomLineTwo == null) {
        break missingId;
      }

      id = R.id.ivChildBusStand;
      ImageView ivChildBusStand = ViewBindings.findChildViewById(rootView, id);
      if (ivChildBusStand == null) {
        break missingId;
      }

      id = R.id.ivInitialChildBusStand;
      ImageView ivInitialChildBusStand = ViewBindings.findChildViewById(rootView, id);
      if (ivInitialChildBusStand == null) {
        break missingId;
      }

      id = R.id.ivTopLine;
      ImageView ivTopLine = ViewBindings.findChildViewById(rootView, id);
      if (ivTopLine == null) {
        break missingId;
      }

      id = R.id.llSecond;
      LinearLayout llSecond = ViewBindings.findChildViewById(rootView, id);
      if (llSecond == null) {
        break missingId;
      }

      id = R.id.tvChangeTheBus;
      TextView tvChangeTheBus = ViewBindings.findChildViewById(rootView, id);
      if (tvChangeTheBus == null) {
        break missingId;
      }

      id = R.id.tvChildBusNme;
      TextView tvChildBusNme = ViewBindings.findChildViewById(rootView, id);
      if (tvChildBusNme == null) {
        break missingId;
      }

      id = R.id.tvChildInternalTime;
      TextView tvChildInternalTime = ViewBindings.findChildViewById(rootView, id);
      if (tvChildInternalTime == null) {
        break missingId;
      }

      id = R.id.tvChildRouteName;
      TextView tvChildRouteName = ViewBindings.findChildViewById(rootView, id);
      if (tvChildRouteName == null) {
        break missingId;
      }

      id = R.id.tvChildTakeBus;
      TextView tvChildTakeBus = ViewBindings.findChildViewById(rootView, id);
      if (tvChildTakeBus == null) {
        break missingId;
      }

      id = R.id.tvReachedTime;
      TextView tvReachedTime = ViewBindings.findChildViewById(rootView, id);
      if (tvReachedTime == null) {
        break missingId;
      }

      id = R.id.tvScheduleTime;
      TextView tvScheduleTime = ViewBindings.findChildViewById(rootView, id);
      if (tvScheduleTime == null) {
        break missingId;
      }

      id = R.id.tvTextReachedTime;
      TextView tvTextReachedTime = ViewBindings.findChildViewById(rootView, id);
      if (tvTextReachedTime == null) {
        break missingId;
      }

      id = R.id.tvTextScheduleTime;
      TextView tvTextScheduleTime = ViewBindings.findChildViewById(rootView, id);
      if (tvTextScheduleTime == null) {
        break missingId;
      }

      return new TempBinding((LinearLayout) rootView, ivChildBottomLine, ivChildBottomLineTwo,
          ivChildBusStand, ivInitialChildBusStand, ivTopLine, llSecond, tvChangeTheBus,
          tvChildBusNme, tvChildInternalTime, tvChildRouteName, tvChildTakeBus, tvReachedTime,
          tvScheduleTime, tvTextReachedTime, tvTextScheduleTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
