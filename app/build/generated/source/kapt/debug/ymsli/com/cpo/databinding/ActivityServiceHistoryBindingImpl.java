package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityServiceHistoryBindingImpl extends ActivityServiceHistoryBinding  {

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
        sViewsWithIds.put(R.id.ccFirsthalf, 4);
        sViewsWithIds.put(R.id.llSecondHalf, 5);
        sViewsWithIds.put(R.id.llEngine, 6);
        sViewsWithIds.put(R.id.tvEngine, 7);
        sViewsWithIds.put(R.id.llChasis, 8);
        sViewsWithIds.put(R.id.tvChassis, 9);
        sViewsWithIds.put(R.id.llEngineType, 10);
        sViewsWithIds.put(R.id.tvEt, 11);
        sViewsWithIds.put(R.id.llAcceleration, 12);
        sViewsWithIds.put(R.id.tvAcc, 13);
        sViewsWithIds.put(R.id.bikeImage, 14);
        sViewsWithIds.put(R.id.ivVehiclePhoto, 15);
        sViewsWithIds.put(R.id.pbProgress, 16);
        sViewsWithIds.put(R.id.svScroll, 17);
        sViewsWithIds.put(R.id.rlServiceRequest, 18);
        sViewsWithIds.put(R.id.tvScheduleServiceHeading, 19);
        sViewsWithIds.put(R.id.progressBar, 20);
        sViewsWithIds.put(R.id.rvAppointment, 21);
        sViewsWithIds.put(R.id.tvNoData, 22);
        sViewsWithIds.put(R.id.rlServiceHistory, 23);
        sViewsWithIds.put(R.id.tvServiceHistoryHeading, 24);
        sViewsWithIds.put(R.id.progressBar1, 25);
        sViewsWithIds.put(R.id.rvHistory, 26);
        sViewsWithIds.put(R.id.tvNoData1, 27);
        sViewsWithIds.put(R.id.btnBookAppointment, 28);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityServiceHistoryBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 29, sIncludes, sViewsWithIds));
    }
    private ActivityServiceHistoryBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[14]
            , (android.widget.Button) bindings[28]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[4]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[1]
            , (android.widget.ImageView) bindings[2]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.ProgressBar) bindings[16]
            , (android.widget.ProgressBar) bindings[20]
            , (android.widget.ProgressBar) bindings[25]
            , (android.widget.RelativeLayout) bindings[23]
            , (android.widget.RelativeLayout) bindings[18]
            , (androidx.recyclerview.widget.RecyclerView) bindings[21]
            , (androidx.recyclerview.widget.RecyclerView) bindings[26]
            , (android.widget.ScrollView) bindings[17]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[24]
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