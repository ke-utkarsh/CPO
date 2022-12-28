// Generated by data binding compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;
import ymsli.com.cpo.R;
import ymsli.com.cpo.data.model.OfferList;

public abstract class ListItemOfferBinding extends ViewDataBinding {
  @NonNull
  public final TextView tvAccept;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvLocation;

  @NonNull
  public final TextView tvOfferPrice;

  @NonNull
  public final TextView tvPhoneNumber;

  @NonNull
  public final TextView tvReject;

  @NonNull
  public final TextView tvUserFullName;

  @NonNull
  public final TextView tvUserShortName;

  @NonNull
  public final View vwDividerLine;

  @NonNull
  public final View vwDividerLine2;

  @Bindable
  protected OfferList mItem;

  protected ListItemOfferBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView tvAccept, TextView tvEmail, TextView tvLocation, TextView tvOfferPrice,
      TextView tvPhoneNumber, TextView tvReject, TextView tvUserFullName, TextView tvUserShortName,
      View vwDividerLine, View vwDividerLine2) {
    super(_bindingComponent, _root, _localFieldCount);
    this.tvAccept = tvAccept;
    this.tvEmail = tvEmail;
    this.tvLocation = tvLocation;
    this.tvOfferPrice = tvOfferPrice;
    this.tvPhoneNumber = tvPhoneNumber;
    this.tvReject = tvReject;
    this.tvUserFullName = tvUserFullName;
    this.tvUserShortName = tvUserShortName;
    this.vwDividerLine = vwDividerLine;
    this.vwDividerLine2 = vwDividerLine2;
  }

  public abstract void setItem(@Nullable OfferList item);

  @Nullable
  public OfferList getItem() {
    return mItem;
  }

  @NonNull
  public static ListItemOfferBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.list_item_offer, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ListItemOfferBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ListItemOfferBinding>inflateInternal(inflater, R.layout.list_item_offer, root, attachToRoot, component);
  }

  @NonNull
  public static ListItemOfferBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.list_item_offer, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ListItemOfferBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ListItemOfferBinding>inflateInternal(inflater, R.layout.list_item_offer, null, false, component);
  }

  public static ListItemOfferBinding bind(@NonNull View view) {
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
  public static ListItemOfferBinding bind(@NonNull View view, @Nullable Object component) {
    return (ListItemOfferBinding)bind(component, view, R.layout.list_item_offer);
  }
}
