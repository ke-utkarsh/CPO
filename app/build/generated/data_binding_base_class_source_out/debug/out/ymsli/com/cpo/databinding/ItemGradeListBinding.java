// Generated by data binding compiler. Do not edit!
package ymsli.com.cpo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;
import ymsli.com.cpo.R;
import ymsli.com.cpo.data.model.AppraisalGrade;

public abstract class ItemGradeListBinding extends ViewDataBinding {
  @Bindable
  protected AppraisalGrade mItem;

  protected ItemGradeListBinding(Object _bindingComponent, View _root, int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setItem(@Nullable AppraisalGrade item);

  @Nullable
  public AppraisalGrade getItem() {
    return mItem;
  }

  @NonNull
  public static ItemGradeListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_grade_list, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemGradeListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemGradeListBinding>inflateInternal(inflater, R.layout.item_grade_list, root, attachToRoot, component);
  }

  @NonNull
  public static ItemGradeListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_grade_list, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemGradeListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemGradeListBinding>inflateInternal(inflater, R.layout.item_grade_list, null, false, component);
  }

  public static ItemGradeListBinding bind(@NonNull View view) {
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
  public static ItemGradeListBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemGradeListBinding)bind(component, view, R.layout.item_grade_list);
  }
}
