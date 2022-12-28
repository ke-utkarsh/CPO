package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityVehicleInformationCustomerBindingImpl extends ActivityVehicleInformationCustomerBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.llScreen, 5);
        sViewsWithIds.put(R.id.ivBack, 6);
        sViewsWithIds.put(R.id.tvNftHead, 7);
        sViewsWithIds.put(R.id.ivVehiclePhoto, 8);
        sViewsWithIds.put(R.id.colorDetailsLayout, 9);
        sViewsWithIds.put(R.id.functionLayout, 10);
        sViewsWithIds.put(R.id.serviceLayout, 11);
        sViewsWithIds.put(R.id.offerLayout, 12);
        sViewsWithIds.put(R.id.certificationLayout, 13);
        sViewsWithIds.put(R.id.nftQrLayout, 14);
        sViewsWithIds.put(R.id.tvVehicalsInfo, 15);
        sViewsWithIds.put(R.id.tvVehicalsPhotos, 16);
        sViewsWithIds.put(R.id.fragmentCustomerContainerView, 17);
        sViewsWithIds.put(R.id.clNormalButtons, 18);
        sViewsWithIds.put(R.id.tvMint, 19);
        sViewsWithIds.put(R.id.tvReject, 20);
        sViewsWithIds.put(R.id.clViewOnly, 21);
        sViewsWithIds.put(R.id.clPrice, 22);
        sViewsWithIds.put(R.id.tvDollar, 23);
        sViewsWithIds.put(R.id.vwDivider, 24);
        sViewsWithIds.put(R.id.etPrice, 25);
        sViewsWithIds.put(R.id.tvOffer, 26);
        sViewsWithIds.put(R.id.progressBar, 27);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityVehicleInformationCustomerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 28, sIncludes, sViewsWithIds));
    }
    private ActivityVehicleInformationCustomerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.LinearLayout) bindings[13]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[18]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[22]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[21]
            , (android.widget.LinearLayout) bindings[9]
            , (android.widget.EditText) bindings[25]
            , (androidx.fragment.app.FragmentContainerView) bindings[17]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.ProgressBar) bindings[27]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[3]
            , (android.view.View) bindings[24]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvColor.setTag(null);
        this.tvModel.setTag(null);
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
        java.lang.String javaLangStringYamahaItemModelJavaLangString = null;
        java.lang.String javaLangStringYamahaItemModel = null;
        java.lang.String javaLangStringYamahaItemModelJavaLangStringItemVIN = null;
        java.lang.String itemYear = null;
        java.lang.String itemModel = null;
        java.lang.String itemColour = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.VIN
                    itemVIN = item.getVIN();
                    // read item.year
                    itemYear = item.getYear();
                    // read item.model
                    itemModel = item.getModel();
                    // read item.colour
                    itemColour = item.getColour();
                }


                // read ("Yamaha ") + (item.model)
                javaLangStringYamahaItemModel = ("Yamaha ") + (itemModel);


                // read (("Yamaha ") + (item.model)) + (" - ")
                javaLangStringYamahaItemModelJavaLangString = (javaLangStringYamahaItemModel) + (" - ");


                // read ((("Yamaha ") + (item.model)) + (" - ")) + (item.VIN)
                javaLangStringYamahaItemModelJavaLangStringItemVIN = (javaLangStringYamahaItemModelJavaLangString) + (itemVIN);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvColor, itemColour);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvModel, itemModel);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvUserName, javaLangStringYamahaItemModelJavaLangStringItemVIN);
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