package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ListItemScheduledAppointmnetBindingImpl extends ListItemScheduledAppointmnetBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.clData, 5);
        sViewsWithIds.put(R.id.tvJobCard, 6);
        sViewsWithIds.put(R.id.llDate, 7);
        sViewsWithIds.put(R.id.ivCalendar, 8);
        sViewsWithIds.put(R.id.llDistance, 9);
        sViewsWithIds.put(R.id.ivDistance, 10);
        sViewsWithIds.put(R.id.tvConcerned, 11);
        sViewsWithIds.put(R.id.ivServiceIcon, 12);
    }
    // views
    @NonNull
    private final android.widget.TextView mboundView4;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ListItemScheduledAppointmnetBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private ListItemScheduledAppointmnetBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[5]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.LinearLayout) bindings[7]
            , (android.widget.LinearLayout) bindings[9]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.parentLayout.setTag(null);
        this.tvDate.setTag(null);
        this.tvDistance.setTag(null);
        this.tvJobCardId.setTag(null);
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
            setItem((ymsli.com.cpo.data.model.AppointmentResult) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable ymsli.com.cpo.data.model.AppointmentResult Item) {
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
        ymsli.com.cpo.data.model.AppointmentResult item = mItem;
        ymsli.com.cpo.data.model.Dealer itemDealer = null;
        java.lang.String itemTime = null;
        java.lang.String itemDealerName = null;
        java.lang.String itemServiceRequestId = null;
        java.lang.String itemDate = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.Dealer
                    itemDealer = item.getDealer();
                    // read item.Time
                    itemTime = item.getTime();
                    // read item.ServiceRequestId
                    itemServiceRequestId = item.getServiceRequestId();
                    // read item.Date
                    itemDate = item.getDate();
                }


                if (itemDealer != null) {
                    // read item.Dealer.Name
                    itemDealerName = itemDealer.getName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, itemDealerName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvDate, itemDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvDistance, itemTime);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvJobCardId, itemServiceRequestId);
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