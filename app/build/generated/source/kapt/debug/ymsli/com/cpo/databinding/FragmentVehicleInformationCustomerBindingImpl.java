package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentVehicleInformationCustomerBindingImpl extends FragmentVehicleInformationCustomerBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.vwTxHash, 9);
        sViewsWithIds.put(R.id.llTxHash, 10);
        sViewsWithIds.put(R.id.tvMid, 11);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentVehicleInformationCustomerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private FragmentVehicleInformationCustomerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            , (android.view.View) bindings[9]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvAccelaration.setTag(null);
        this.tvChassisNumber.setTag(null);
        this.tvDisplacement.setTag(null);
        this.tvEngineNumber.setTag(null);
        this.tvTopSpeed.setTag(null);
        this.tvTxHash.setTag(null);
        this.tvVin.setTag(null);
        this.tvYid.setTag(null);
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
            setItem((ymsli.com.cpo.data.model.VehicleInformationData) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable ymsli.com.cpo.data.model.VehicleInformationData Item) {
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
        java.lang.String itemVIN = null;
        ymsli.com.cpo.data.model.VehicleInformationData item = mItem;
        java.lang.String itemTopSpeed = null;
        java.lang.String item0To100 = null;
        java.lang.String itemChassisNo = null;
        java.lang.String itemTxHashToString = null;
        java.lang.String itemEngineNo = null;
        java.lang.Object itemTxHash = null;
        java.lang.String itemDisplacement = null;
        java.lang.String itemYID = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.VIN
                    itemVIN = item.getVIN();
                    // read item.topSpeed
                    itemTopSpeed = item.getTopSpeed();
                    // read item._0_to_100
                    item0To100 = item.get_0_to_100();
                    // read item.chassisNo
                    itemChassisNo = item.getChassisNo();
                    // read item.engineNo
                    itemEngineNo = item.getEngineNo();
                    // read item.txHash
                    itemTxHash = item.getTxHash();
                    // read item.displacement
                    itemDisplacement = item.getDisplacement();
                    // read item.YID
                    itemYID = item.getYID();
                }


                if (itemTxHash != null) {
                    // read item.txHash.toString()
                    itemTxHashToString = itemTxHash.toString();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvAccelaration, item0To100);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvChassisNumber, itemChassisNo);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvDisplacement, itemDisplacement);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvEngineNumber, itemEngineNo);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTopSpeed, itemTopSpeed);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTxHash, itemTxHashToString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvVin, itemVIN);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvYid, itemYID);
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