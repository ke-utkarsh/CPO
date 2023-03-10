// Generated by data binding compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;
import ymsli.com.cpo.R;
import ymsli.com.cpo.data.model.ActiveWarrantiesData;

public abstract class ListitemWarrantiesBinding extends ViewDataBinding {
  @NonNull
  public final Button btnCreateNft;

  @NonNull
  public final ConstraintLayout clData;

  @NonNull
  public final CardView cvWarranties;

  @NonNull
  public final ImageView ivVehiclePhoto;

  @NonNull
  public final LinearLayout llColor;

  @NonNull
  public final LinearLayout llOwnership;

  @NonNull
  public final LinearLayout llYear;

  @NonNull
  public final ProgressBar pbProgress;

  @NonNull
  public final TextView tvBikeName;

  @NonNull
  public final TextView tvBikeNumber;

  @NonNull
  public final TextView tvChassisNumber;

  @NonNull
  public final TextView tvColor;

  @NonNull
  public final TextView tvColorTag;

  @NonNull
  public final TextView tvDate;

  @NonNull
  public final TextView tvEngineNumber;

  @NonNull
  public final TextView tvOwnership;

  @NonNull
  public final TextView tvOwnershipTag;

  @NonNull
  public final TextView tvUserName;

  @NonNull
  public final TextView tvYear;

  @NonNull
  public final TextView tvYearTag;

  @Bindable
  protected ActiveWarrantiesData mItem;

  protected ListitemWarrantiesBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnCreateNft, ConstraintLayout clData, CardView cvWarranties, ImageView ivVehiclePhoto,
      LinearLayout llColor, LinearLayout llOwnership, LinearLayout llYear, ProgressBar pbProgress,
      TextView tvBikeName, TextView tvBikeNumber, TextView tvChassisNumber, TextView tvColor,
      TextView tvColorTag, TextView tvDate, TextView tvEngineNumber, TextView tvOwnership,
      TextView tvOwnershipTag, TextView tvUserName, TextView tvYear, TextView tvYearTag) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnCreateNft = btnCreateNft;
    this.clData = clData;
    this.cvWarranties = cvWarranties;
    this.ivVehiclePhoto = ivVehiclePhoto;
    this.llColor = llColor;
    this.llOwnership = llOwnership;
    this.llYear = llYear;
    this.pbProgress = pbProgress;
    this.tvBikeName = tvBikeName;
    this.tvBikeNumber = tvBikeNumber;
    this.tvChassisNumber = tvChassisNumber;
    this.tvColor = tvColor;
    this.tvColorTag = tvColorTag;
    this.tvDate = tvDate;
    this.tvEngineNumber = tvEngineNumber;
    this.tvOwnership = tvOwnership;
    this.tvOwnershipTag = tvOwnershipTag;
    this.tvUserName = tvUserName;
    this.tvYear = tvYear;
    this.tvYearTag = tvYearTag;
  }

  public abstract void setItem(@Nullable ActiveWarrantiesData item);

  @Nullable
  public ActiveWarrantiesData getItem() {
    return mItem;
  }

  @NonNull
  public static ListitemWarrantiesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.listitem_warranties, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ListitemWarrantiesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ListitemWarrantiesBinding>inflateInternal(inflater, R.layout.listitem_warranties, root, attachToRoot, component);
  }

  @NonNull
  public static ListitemWarrantiesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.listitem_warranties, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ListitemWarrantiesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ListitemWarrantiesBinding>inflateInternal(inflater, R.layout.listitem_warranties, null, false, component);
  }

  public static ListitemWarrantiesBinding bind(@NonNull View view) {
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
  public static ListitemWarrantiesBinding bind(@NonNull View view, @Nullable Object component) {
    return (ListitemWarrantiesBinding)bind(component, view, R.layout.listitem_warranties);
  }
}
