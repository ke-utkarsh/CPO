package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ListItemOfferBindingImpl extends ListItemOfferBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tvUserShortName, 7);
        sViewsWithIds.put(R.id.vwDividerLine, 8);
        sViewsWithIds.put(R.id.vwDividerLine2, 9);
        sViewsWithIds.put(R.id.tvReject, 10);
        sViewsWithIds.put(R.id.tvAccept, 11);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView2;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ListItemOfferBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private ListItemOfferBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[7]
            , (android.view.View) bindings[8]
            , (android.view.View) bindings[9]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.tvEmail.setTag(null);
        this.tvLocation.setTag(null);
        this.tvOfferPrice.setTag(null);
        this.tvPhoneNumber.setTag(null);
        this.tvUserFullName.setTag(null);
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
            setItem((ymsli.com.cpo.data.model.OfferList) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable ymsli.com.cpo.data.model.OfferList Item) {
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
        java.lang.String javaLangStringYIDItemYid = null;
        java.lang.String javaLangStringItemOfferPriceJavaLangString = null;
        java.lang.String itemYid = null;
        java.lang.String javaLangStringItemOfferPrice = null;
        ymsli.com.cpo.data.model.OfferList item = mItem;
        java.lang.String itemPhoneNumber = null;
        java.lang.String itemAddress = null;
        java.lang.String itemName = null;
        java.lang.String itemEmail = null;
        int itemOfferPrice = 0;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.yid
                    itemYid = item.getYid();
                    // read item.phoneNumber
                    itemPhoneNumber = item.getPhoneNumber();
                    // read item.address
                    itemAddress = item.getAddress();
                    // read item.name
                    itemName = item.getName();
                    // read item.email
                    itemEmail = item.getEmail();
                    // read item.offerPrice
                    itemOfferPrice = item.getOfferPrice();
                }


                // read ("YID - ") + (item.yid)
                javaLangStringYIDItemYid = ("YID - ") + (itemYid);
                // read ("$ ") + (item.offerPrice)
                javaLangStringItemOfferPrice = ("$ ") + (itemOfferPrice);


                // read (("$ ") + (item.offerPrice)) + ("/-")
                javaLangStringItemOfferPriceJavaLangString = (javaLangStringItemOfferPrice) + ("/-");
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, javaLangStringYIDItemYid);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvEmail, itemEmail);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvLocation, itemAddress);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvOfferPrice, javaLangStringItemOfferPriceJavaLangString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvPhoneNumber, itemPhoneNumber);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvUserFullName, itemName);
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