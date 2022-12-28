package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemAppraisalHistoryBindingImpl extends ItemAppraisalHistoryBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.clData, 4);
        sViewsWithIds.put(R.id.llDate, 5);
        sViewsWithIds.put(R.id.ivCalendar, 6);
        sViewsWithIds.put(R.id.llDistance, 7);
        sViewsWithIds.put(R.id.ivDistance, 8);
        sViewsWithIds.put(R.id.ivNext, 9);
        sViewsWithIds.put(R.id.ivIcon, 10);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemAppraisalHistoryBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds));
    }
    private ItemAppraisalHistoryBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[4]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[7]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[1]
            );
        this.parentLayout.setTag(null);
        this.tvDate.setTag(null);
        this.tvDistance.setTag(null);
        this.tvHeading.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.item == variableId) {
            setItem((ymsli.com.cpo.data.model.AppraisalHistoryResult) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable ymsli.com.cpo.data.model.AppraisalHistoryResult Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String javaLangStringGradeItemGradeJavaLangString = null;
        ymsli.com.cpo.data.model.AppraisalHistoryResult item = mItem;
        java.lang.String itemVehicleCondition = null;
        java.lang.String itemDate = null;
        java.lang.String javaLangStringGradeItemGradeJavaLangStringItemVehicleCondition = null;
        java.lang.String itemTripMeterReading = null;
        java.lang.String itemGrade = null;
        java.lang.String javaLangStringGradeItemGrade = null;
        java.lang.String javaLangStringGradeItemGradeJavaLangStringItemVehicleConditionJavaLangString = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.vehicleCondition
                    itemVehicleCondition = item.getVehicleCondition();
                    // read item.date
                    itemDate = item.getDate();
                    // read item.tripMeterReading
                    itemTripMeterReading = item.getTripMeterReading();
                    // read item.grade
                    itemGrade = item.getGrade();
                }


                // read ("Grade ") + (item.grade)
                javaLangStringGradeItemGrade = ("Grade ") + (itemGrade);


                // read (("Grade ") + (item.grade)) + (" (")
                javaLangStringGradeItemGradeJavaLangString = (javaLangStringGradeItemGrade) + (" (");


                // read ((("Grade ") + (item.grade)) + (" (")) + (item.vehicleCondition)
                javaLangStringGradeItemGradeJavaLangStringItemVehicleCondition = (javaLangStringGradeItemGradeJavaLangString) + (itemVehicleCondition);


                // read (((("Grade ") + (item.grade)) + (" (")) + (item.vehicleCondition)) + (")")
                javaLangStringGradeItemGradeJavaLangStringItemVehicleConditionJavaLangString = (javaLangStringGradeItemGradeJavaLangStringItemVehicleCondition) + (")");
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvDate, itemDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvDistance, itemTripMeterReading);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvHeading, javaLangStringGradeItemGradeJavaLangStringItemVehicleConditionJavaLangString);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}