package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityProfileBindingImpl extends ActivityProfileBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.ivBack, 10);
        sViewsWithIds.put(R.id.cryptoWallet, 11);
        sViewsWithIds.put(R.id.tvLink, 12);
        sViewsWithIds.put(R.id.llWalletAddress, 13);
        sViewsWithIds.put(R.id.llTransactionHash, 14);
        sViewsWithIds.put(R.id.progressBar, 15);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    @NonNull
    private final android.widget.TextView mboundView6;
    @NonNull
    private final android.widget.TextView mboundView7;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityProfileBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 16, sIncludes, sViewsWithIds));
    }
    private ActivityProfileBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.LinearLayout) bindings[13]
            , (android.widget.ProgressBar) bindings[15]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (android.widget.TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (android.widget.TextView) bindings[7];
        this.mboundView7.setTag(null);
        this.tvCryptoWalletAddress.setTag(null);
        this.tvTransactionHash.setTag(null);
        this.tvUserName.setTag(null);
        this.tvUserShortName.setTag(null);
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
            setItem((ymsli.com.cpo.data.model.ProfileData) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable ymsli.com.cpo.data.model.ProfileData Item) {
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
        java.lang.String itemInitials = null;
        ymsli.com.cpo.data.model.ProfileData item = mItem;
        java.lang.String itemWalletAddress = null;
        java.lang.String itemPhoneNumber = null;
        java.lang.String itemAddress = null;
        java.lang.String itemFullName = null;
        java.lang.String itemEmail = null;
        java.lang.String itemDOB = null;
        java.lang.String itemTxHash = null;
        java.lang.String itemYID = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.initials
                    itemInitials = item.getInitials();
                    // read item.walletAddress
                    itemWalletAddress = item.getWalletAddress();
                    // read item.phoneNumber
                    itemPhoneNumber = item.getPhoneNumber();
                    // read item.address
                    itemAddress = item.getAddress();
                    // read item.fullName
                    itemFullName = item.getFullName();
                    // read item.email
                    itemEmail = item.getEmail();
                    // read item.DOB
                    itemDOB = item.getDOB();
                    // read item.txHash
                    itemTxHash = item.getTxHash();
                    // read item.YID
                    itemYID = item.getYID();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, itemYID);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, itemDOB);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, itemPhoneNumber);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView6, itemEmail);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView7, itemAddress);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCryptoWalletAddress, itemWalletAddress);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTransactionHash, itemTxHash);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvUserName, itemFullName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvUserShortName, itemInitials);
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