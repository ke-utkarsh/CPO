// Generated by data binding compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;
import java.lang.Deprecated;
import java.lang.Object;
import ymsli.com.cpo.R;

public abstract class ActivityCustomerTitleBinding extends ViewDataBinding {
  @NonNull
  public final ConstraintLayout clAppNft;

  @NonNull
  public final ConstraintLayout clNft;

  @NonNull
  public final ConstraintLayout clToolbar;

  @NonNull
  public final DrawerLayout drawerlayout;

  @NonNull
  public final ImageView ivLogout;

  @NonNull
  public final ImageView ivNotification;

  @NonNull
  public final ImageView ivWindow;

  @NonNull
  public final NavigationView navView;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RecyclerView rvNft;

  @NonNull
  public final TextView tvApproveNFT;

  @NonNull
  public final TextView tvNFT;

  @NonNull
  public final TextView tvNoData;

  @NonNull
  public final TextView tvNumberApproveNfts;

  @NonNull
  public final TextView tvNumberNfts;

  protected ActivityCustomerTitleBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ConstraintLayout clAppNft, ConstraintLayout clNft, ConstraintLayout clToolbar,
      DrawerLayout drawerlayout, ImageView ivLogout, ImageView ivNotification, ImageView ivWindow,
      NavigationView navView, ProgressBar progressBar, RecyclerView rvNft, TextView tvApproveNFT,
      TextView tvNFT, TextView tvNoData, TextView tvNumberApproveNfts, TextView tvNumberNfts) {
    super(_bindingComponent, _root, _localFieldCount);
    this.clAppNft = clAppNft;
    this.clNft = clNft;
    this.clToolbar = clToolbar;
    this.drawerlayout = drawerlayout;
    this.ivLogout = ivLogout;
    this.ivNotification = ivNotification;
    this.ivWindow = ivWindow;
    this.navView = navView;
    this.progressBar = progressBar;
    this.rvNft = rvNft;
    this.tvApproveNFT = tvApproveNFT;
    this.tvNFT = tvNFT;
    this.tvNoData = tvNoData;
    this.tvNumberApproveNfts = tvNumberApproveNfts;
    this.tvNumberNfts = tvNumberNfts;
  }

  @NonNull
  public static ActivityCustomerTitleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_customer_title, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityCustomerTitleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityCustomerTitleBinding>inflateInternal(inflater, R.layout.activity_customer_title, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityCustomerTitleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_customer_title, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityCustomerTitleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityCustomerTitleBinding>inflateInternal(inflater, R.layout.activity_customer_title, null, false, component);
  }

  public static ActivityCustomerTitleBinding bind(@NonNull View view) {
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
  public static ActivityCustomerTitleBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityCustomerTitleBinding)bind(component, view, R.layout.activity_customer_title);
  }
}