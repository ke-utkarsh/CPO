// Generated by data binding compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;
import ymsli.com.cpo.R;

public abstract class DialogRejectBinding extends ViewDataBinding {
  @NonNull
  public final TextView tvCancel;

  @NonNull
  public final TextView tvSubmit;

  protected DialogRejectBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextView tvCancel, TextView tvSubmit) {
    super(_bindingComponent, _root, _localFieldCount);
    this.tvCancel = tvCancel;
    this.tvSubmit = tvSubmit;
  }

  @NonNull
  public static DialogRejectBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dialog_reject, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static DialogRejectBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<DialogRejectBinding>inflateInternal(inflater, R.layout.dialog_reject, root, attachToRoot, component);
  }

  @NonNull
  public static DialogRejectBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dialog_reject, null, false, component)
   */
  @NonNull
  @Deprecated
  public static DialogRejectBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<DialogRejectBinding>inflateInternal(inflater, R.layout.dialog_reject, null, false, component);
  }

  public static DialogRejectBinding bind(@NonNull View view) {
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
  public static DialogRejectBinding bind(@NonNull View view, @Nullable Object component) {
    return (DialogRejectBinding)bind(component, view, R.layout.dialog_reject);
  }
}
