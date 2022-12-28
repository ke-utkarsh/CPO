// Generated by data binding compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;
import ymsli.com.cpo.R;
import ymsli.com.cpo.data.model.ProfileData;

public abstract class ActivityProfileBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout cryptoWallet;

  @NonNull
  public final ImageView ivBack;

  @NonNull
  public final LinearLayout llTransactionHash;

  @NonNull
  public final LinearLayout llWalletAddress;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final TextView tvCryptoWalletAddress;

  @NonNull
  public final TextView tvLink;

  @NonNull
  public final TextView tvTransactionHash;

  @NonNull
  public final TextView tvUserName;

  @NonNull
  public final TextView tvUserShortName;

  @Bindable
  protected ProfileData mItem;

  protected ActivityProfileBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout cryptoWallet, ImageView ivBack, LinearLayout llTransactionHash,
      LinearLayout llWalletAddress, ProgressBar progressBar, TextView tvCryptoWalletAddress,
      TextView tvLink, TextView tvTransactionHash, TextView tvUserName, TextView tvUserShortName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cryptoWallet = cryptoWallet;
    this.ivBack = ivBack;
    this.llTransactionHash = llTransactionHash;
    this.llWalletAddress = llWalletAddress;
    this.progressBar = progressBar;
    this.tvCryptoWalletAddress = tvCryptoWalletAddress;
    this.tvLink = tvLink;
    this.tvTransactionHash = tvTransactionHash;
    this.tvUserName = tvUserName;
    this.tvUserShortName = tvUserShortName;
  }

  public abstract void setItem(@Nullable ProfileData item);

  @Nullable
  public ProfileData getItem() {
    return mItem;
  }

  @NonNull
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_profile, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityProfileBinding>inflateInternal(inflater, R.layout.activity_profile, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_profile, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityProfileBinding>inflateInternal(inflater, R.layout.activity_profile, null, false, component);
  }

  public static ActivityProfileBinding bind(@NonNull View view) {
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
  public static ActivityProfileBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityProfileBinding)bind(component, view, R.layout.activity_profile);
  }
}