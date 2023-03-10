// Generated by data binding compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.Deprecated;
import java.lang.Object;
import ymsli.com.cpo.R;

public abstract class ActivityServiceHistoryBinding extends ViewDataBinding {
  @NonNull
  public final ConstraintLayout bikeImage;

  @NonNull
  public final Button btnBookAppointment;

  @NonNull
  public final ConstraintLayout ccFirsthalf;

  @NonNull
  public final ConstraintLayout clToolbar;

  @NonNull
  public final ImageView ivBack;

  @NonNull
  public final ImageView ivVehiclePhoto;

  @NonNull
  public final LinearLayout llAcceleration;

  @NonNull
  public final LinearLayout llChasis;

  @NonNull
  public final LinearLayout llEngine;

  @NonNull
  public final LinearLayout llEngineType;

  @NonNull
  public final LinearLayout llSecondHalf;

  @NonNull
  public final ProgressBar pbProgress;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final ProgressBar progressBar1;

  @NonNull
  public final RelativeLayout rlServiceHistory;

  @NonNull
  public final RelativeLayout rlServiceRequest;

  @NonNull
  public final RecyclerView rvAppointment;

  @NonNull
  public final RecyclerView rvHistory;

  @NonNull
  public final ScrollView svScroll;

  @NonNull
  public final TextView tvAcc;

  @NonNull
  public final TextView tvChassis;

  @NonNull
  public final TextView tvEngine;

  @NonNull
  public final TextView tvEt;

  @NonNull
  public final TextView tvNoData;

  @NonNull
  public final TextView tvNoData1;

  @NonNull
  public final TextView tvPageHead;

  @NonNull
  public final TextView tvScheduleServiceHeading;

  @NonNull
  public final TextView tvServiceHistoryHeading;

  protected ActivityServiceHistoryBinding(Object _bindingComponent, View _root,
      int _localFieldCount, ConstraintLayout bikeImage, Button btnBookAppointment,
      ConstraintLayout ccFirsthalf, ConstraintLayout clToolbar, ImageView ivBack,
      ImageView ivVehiclePhoto, LinearLayout llAcceleration, LinearLayout llChasis,
      LinearLayout llEngine, LinearLayout llEngineType, LinearLayout llSecondHalf,
      ProgressBar pbProgress, ProgressBar progressBar, ProgressBar progressBar1,
      RelativeLayout rlServiceHistory, RelativeLayout rlServiceRequest, RecyclerView rvAppointment,
      RecyclerView rvHistory, ScrollView svScroll, TextView tvAcc, TextView tvChassis,
      TextView tvEngine, TextView tvEt, TextView tvNoData, TextView tvNoData1, TextView tvPageHead,
      TextView tvScheduleServiceHeading, TextView tvServiceHistoryHeading) {
    super(_bindingComponent, _root, _localFieldCount);
    this.bikeImage = bikeImage;
    this.btnBookAppointment = btnBookAppointment;
    this.ccFirsthalf = ccFirsthalf;
    this.clToolbar = clToolbar;
    this.ivBack = ivBack;
    this.ivVehiclePhoto = ivVehiclePhoto;
    this.llAcceleration = llAcceleration;
    this.llChasis = llChasis;
    this.llEngine = llEngine;
    this.llEngineType = llEngineType;
    this.llSecondHalf = llSecondHalf;
    this.pbProgress = pbProgress;
    this.progressBar = progressBar;
    this.progressBar1 = progressBar1;
    this.rlServiceHistory = rlServiceHistory;
    this.rlServiceRequest = rlServiceRequest;
    this.rvAppointment = rvAppointment;
    this.rvHistory = rvHistory;
    this.svScroll = svScroll;
    this.tvAcc = tvAcc;
    this.tvChassis = tvChassis;
    this.tvEngine = tvEngine;
    this.tvEt = tvEt;
    this.tvNoData = tvNoData;
    this.tvNoData1 = tvNoData1;
    this.tvPageHead = tvPageHead;
    this.tvScheduleServiceHeading = tvScheduleServiceHeading;
    this.tvServiceHistoryHeading = tvServiceHistoryHeading;
  }

  @NonNull
  public static ActivityServiceHistoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_service_history, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityServiceHistoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityServiceHistoryBinding>inflateInternal(inflater, R.layout.activity_service_history, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityServiceHistoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_service_history, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityServiceHistoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityServiceHistoryBinding>inflateInternal(inflater, R.layout.activity_service_history, null, false, component);
  }

  public static ActivityServiceHistoryBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityServiceHistoryBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityServiceHistoryBinding)bind(component, view, R.layout.activity_service_history);
  }
}
