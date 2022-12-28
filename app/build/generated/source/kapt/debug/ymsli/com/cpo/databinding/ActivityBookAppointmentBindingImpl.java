package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityBookAppointmentBindingImpl extends ActivityBookAppointmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ivBack, 5);
        sViewsWithIds.put(R.id.labelSelectDate, 6);
        sViewsWithIds.put(R.id.progressBar, 7);
        sViewsWithIds.put(R.id.rvSelectDate, 8);
        sViewsWithIds.put(R.id.labelSelectTime, 9);
        sViewsWithIds.put(R.id.rvSelectTime, 10);
        sViewsWithIds.put(R.id.labelSelectedDate, 11);
        sViewsWithIds.put(R.id.llSelectedDate, 12);
        sViewsWithIds.put(R.id.tvSelectedDate, 13);
        sViewsWithIds.put(R.id.tvSelectedTime, 14);
        sViewsWithIds.put(R.id.rvConcernedIssue, 15);
        sViewsWithIds.put(R.id.addConcernedIssue, 16);
        sViewsWithIds.put(R.id.etTripMeterReading, 17);
        sViewsWithIds.put(R.id.rvTripMeterReadingImages, 18);
        sViewsWithIds.put(R.id.cardClickTripMeterImage, 19);
        sViewsWithIds.put(R.id.ivClickTripMasterImage, 20);
        sViewsWithIds.put(R.id.tripMasterImageProgress, 21);
        sViewsWithIds.put(R.id.btnBookAppointment, 22);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityBookAppointmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }
    private ActivityBookAppointmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[16]
            , (android.widget.Button) bindings[22]
            , (androidx.cardview.widget.CardView) bindings[19]
            , (android.widget.EditText) bindings[17]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.ImageView) bindings[20]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[11]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.ProgressBar) bindings[7]
            , (androidx.recyclerview.widget.RecyclerView) bindings[15]
            , (androidx.recyclerview.widget.RecyclerView) bindings[8]
            , (androidx.recyclerview.widget.RecyclerView) bindings[10]
            , (androidx.recyclerview.widget.RecyclerView) bindings[18]
            , (android.widget.ProgressBar) bindings[21]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[14]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
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
            setItem((ymsli.com.cpo.data.model.DealersResult) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable ymsli.com.cpo.data.model.DealersResult Item) {
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
        java.lang.String itemAddress = null;
        ymsli.com.cpo.data.model.DealersResult item = mItem;
        java.lang.String itemDistanceScale = null;
        java.lang.String itemDistanceJavaLangStringItemDistanceScale = null;
        java.lang.String itemPhone = null;
        int itemDistance = 0;
        java.lang.String itemDistanceJavaLangStringItemDistanceScaleJavaLangStringAway = null;
        java.lang.String itemName = null;
        java.lang.String itemDistanceJavaLangString = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.Address
                    itemAddress = item.getAddress();
                    // read item.DistanceScale
                    itemDistanceScale = item.getDistanceScale();
                    // read item.Phone
                    itemPhone = item.getPhone();
                    // read item.Distance
                    itemDistance = item.getDistance();
                    // read item.Name
                    itemName = item.getName();
                }


                // read (item.Distance) + (" ")
                itemDistanceJavaLangString = (itemDistance) + (" ");


                // read ((item.Distance) + (" ")) + (item.DistanceScale)
                itemDistanceJavaLangStringItemDistanceScale = (itemDistanceJavaLangString) + (itemDistanceScale);


                // read (((item.Distance) + (" ")) + (item.DistanceScale)) + (" away ")
                itemDistanceJavaLangStringItemDistanceScaleJavaLangStringAway = (itemDistanceJavaLangStringItemDistanceScale) + (" away ");
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, itemName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, itemDistanceJavaLangStringItemDistanceScaleJavaLangStringAway);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, itemPhone);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, itemAddress);
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