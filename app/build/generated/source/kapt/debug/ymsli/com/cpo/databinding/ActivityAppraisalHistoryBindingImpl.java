package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityAppraisalHistoryBindingImpl extends ActivityAppraisalHistoryBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.clToolbar, 1);
        sViewsWithIds.put(R.id.ivBack, 2);
        sViewsWithIds.put(R.id.tvPageHead, 3);
        sViewsWithIds.put(R.id.constraintLayout2, 4);
        sViewsWithIds.put(R.id.ccFirsthalf, 5);
        sViewsWithIds.put(R.id.llSecondHalf, 6);
        sViewsWithIds.put(R.id.llEngine, 7);
        sViewsWithIds.put(R.id.tvEngine, 8);
        sViewsWithIds.put(R.id.llChasis, 9);
        sViewsWithIds.put(R.id.tvChassis, 10);
        sViewsWithIds.put(R.id.llEngineType, 11);
        sViewsWithIds.put(R.id.tvEt, 12);
        sViewsWithIds.put(R.id.llAcceleration, 13);
        sViewsWithIds.put(R.id.tvAcc, 14);
        sViewsWithIds.put(R.id.bikeImage, 15);
        sViewsWithIds.put(R.id.ivVehiclePhoto, 16);
        sViewsWithIds.put(R.id.pbProgress, 17);
        sViewsWithIds.put(R.id.pbProgressBar, 18);
        sViewsWithIds.put(R.id.tvAppraisalHistory, 19);
        sViewsWithIds.put(R.id.tvNoData, 20);
        sViewsWithIds.put(R.id.rvAppraisalHistory, 21);
        sViewsWithIds.put(R.id.btnRequestAppraisal, 22);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityAppraisalHistoryBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }
    private ActivityAppraisalHistoryBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[15]
            , (android.widget.Button) bindings[22]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[1]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[4]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.ImageView) bindings[16]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.ProgressBar) bindings[17]
            , (android.widget.ProgressBar) bindings[18]
            , (androidx.recyclerview.widget.RecyclerView) bindings[21]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[3]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
            return variableSet;
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}