package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemDealerNearMeBindingImpl extends ItemDealerNearMeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ivDealerImage, 6);
        sViewsWithIds.put(R.id.vwDivider1, 7);
        sViewsWithIds.put(R.id.clData, 8);
        sViewsWithIds.put(R.id.ivLocation, 9);
        sViewsWithIds.put(R.id.vwDivider2, 10);
        sViewsWithIds.put(R.id.btnBook, 11);
        sViewsWithIds.put(R.id.ivBook, 12);
    }
    // views
    @NonNull
    private final androidx.cardview.widget.CardView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemDealerNearMeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private ItemDealerNearMeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[11]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[8]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[3]
            , (android.view.View) bindings[7]
            , (android.view.View) bindings[10]
            );
        this.mboundView0 = (androidx.cardview.widget.CardView) bindings[0];
        this.mboundView0.setTag(null);
        this.tvDelerName.setTag(null);
        this.tvDistance.setTag(null);
        this.tvEmail.setTag(null);
        this.tvLocation.setTag(null);
        this.tvPhone.setTag(null);
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
        java.lang.String itemPhone = null;
        java.lang.String itemAddress = null;
        ymsli.com.cpo.data.model.DealersResult item = mItem;
        java.lang.String itemDistanceScale = null;
        int itemDistance = 0;
        java.lang.String itemEmail = null;
        java.lang.String itemName = null;
        java.lang.String itemDistanceJavaLangStringItemDistanceScale = null;
        java.lang.String itemDistanceJavaLangString = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.Phone
                    itemPhone = item.getPhone();
                    // read item.Address
                    itemAddress = item.getAddress();
                    // read item.DistanceScale
                    itemDistanceScale = item.getDistanceScale();
                    // read item.Distance
                    itemDistance = item.getDistance();
                    // read item.Email
                    itemEmail = item.getEmail();
                    // read item.Name
                    itemName = item.getName();
                }


                // read (item.Distance) + (" ")
                itemDistanceJavaLangString = (itemDistance) + (" ");


                // read ((item.Distance) + (" ")) + (item.DistanceScale)
                itemDistanceJavaLangStringItemDistanceScale = (itemDistanceJavaLangString) + (itemDistanceScale);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvDelerName, itemName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvDistance, itemDistanceJavaLangStringItemDistanceScale);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvEmail, itemEmail);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvLocation, itemAddress);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvPhone, itemPhone);
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