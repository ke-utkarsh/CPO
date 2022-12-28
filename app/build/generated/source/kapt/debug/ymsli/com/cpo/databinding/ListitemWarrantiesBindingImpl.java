package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ListitemWarrantiesBindingImpl extends ListitemWarrantiesBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ivVehiclePhoto, 9);
        sViewsWithIds.put(R.id.pbProgress, 10);
        sViewsWithIds.put(R.id.clData, 11);
        sViewsWithIds.put(R.id.llColor, 12);
        sViewsWithIds.put(R.id.tvColorTag, 13);
        sViewsWithIds.put(R.id.llYear, 14);
        sViewsWithIds.put(R.id.tvYearTag, 15);
        sViewsWithIds.put(R.id.llOwnership, 16);
        sViewsWithIds.put(R.id.tvOwnershipTag, 17);
        sViewsWithIds.put(R.id.tvOwnership, 18);
        sViewsWithIds.put(R.id.btnCreateNft, 19);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ListitemWarrantiesBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }
    private ListitemWarrantiesBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[19]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[11]
            , (androidx.cardview.widget.CardView) bindings[0]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[16]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.ProgressBar) bindings[10]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[15]
            );
        this.cvWarranties.setTag(null);
        this.tvBikeName.setTag(null);
        this.tvBikeNumber.setTag(null);
        this.tvChassisNumber.setTag(null);
        this.tvColor.setTag(null);
        this.tvDate.setTag(null);
        this.tvEngineNumber.setTag(null);
        this.tvUserName.setTag(null);
        this.tvYear.setTag(null);
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
            setItem((ymsli.com.cpo.data.model.ActiveWarrantiesData) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable ymsli.com.cpo.data.model.ActiveWarrantiesData Item) {
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
        java.lang.String itemCustomerName = null;
        java.lang.String itemVIN = null;
        ymsli.com.cpo.data.model.ActiveWarrantiesData item = mItem;
        java.lang.String itemEngineNo = null;
        java.lang.String itemModel = null;
        java.lang.String itemDate = null;
        java.lang.String itemChassisNo = null;
        java.lang.String itemYear = null;
        java.lang.String itemColour = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.customerName
                    itemCustomerName = item.getCustomerName();
                    // read item.VIN
                    itemVIN = item.getVIN();
                    // read item.EngineNo
                    itemEngineNo = item.getEngineNo();
                    // read item.Model
                    itemModel = item.getModel();
                    // read item.date
                    itemDate = item.getDate();
                    // read item.ChassisNo
                    itemChassisNo = item.getChassisNo();
                    // read item.Year
                    itemYear = item.getYear();
                    // read item.Colour
                    itemColour = item.getColour();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvBikeName, itemModel);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvBikeNumber, itemVIN);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvChassisNumber, itemChassisNo);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvColor, itemColour);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvDate, itemDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvEngineNumber, itemEngineNo);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvUserName, itemCustomerName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvYear, itemYear);
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