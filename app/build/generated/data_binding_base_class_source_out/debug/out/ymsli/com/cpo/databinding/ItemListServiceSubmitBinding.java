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
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.Deprecated;
import java.lang.Object;
import ymsli.com.cpo.R;

public abstract class ItemListServiceSubmitBinding extends ViewDataBinding {
  @NonNull
  public final ProgressBar beforeImageProgress;

  @NonNull
  public final ImageView ivClickBeforeImage;

  @NonNull
  public final RecyclerView rvAfterImages;

  @NonNull
  public final RecyclerView rvBeforeImages;

  @NonNull
  public final TextView tvIssueHead;

  @NonNull
  public final View vwDividerLine;

  protected ItemListServiceSubmitBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ProgressBar beforeImageProgress, ImageView ivClickBeforeImage, RecyclerView rvAfterImages,
      RecyclerView rvBeforeImages, TextView tvIssueHead, View vwDividerLine) {
    super(_bindingComponent, _root, _localFieldCount);
    this.beforeImageProgress = beforeImageProgress;
    this.ivClickBeforeImage = ivClickBeforeImage;
    this.rvAfterImages = rvAfterImages;
    this.rvBeforeImages = rvBeforeImages;
    this.tvIssueHead = tvIssueHead;
    this.vwDividerLine = vwDividerLine;
  }

  @NonNull
  public static ItemListServiceSubmitBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_list_service_submit, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemListServiceSubmitBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemListServiceSubmitBinding>inflateInternal(inflater, R.layout.item_list_service_submit, root, attachToRoot, component);
  }

  @NonNull
  public static ItemListServiceSubmitBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_list_service_submit, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemListServiceSubmitBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemListServiceSubmitBinding>inflateInternal(inflater, R.layout.item_list_service_submit, null, false, component);
  }

  public static ItemListServiceSubmitBinding bind(@NonNull View view) {
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
  public static ItemListServiceSubmitBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemListServiceSubmitBinding)bind(component, view, R.layout.item_list_service_submit);
  }
}
