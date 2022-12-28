package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityServiceHistoryDetailBindingImpl extends ActivityServiceHistoryDetailBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.clToolbar, 9);
        sViewsWithIds.put(R.id.ivBack, 10);
        sViewsWithIds.put(R.id.clJobCardIntro, 11);
        sViewsWithIds.put(R.id.ivServiceIcon, 12);
        sViewsWithIds.put(R.id.tvJobVin, 13);
        sViewsWithIds.put(R.id.tvTxHashHeading, 14);
        sViewsWithIds.put(R.id.tvMetadataHeading, 15);
        sViewsWithIds.put(R.id.vwDividerLine, 16);
        sViewsWithIds.put(R.id.ivServiceCenter, 17);
        sViewsWithIds.put(R.id.llDate, 18);
        sViewsWithIds.put(R.id.ivCalendar, 19);
        sViewsWithIds.put(R.id.llTripMeter, 20);
        sViewsWithIds.put(R.id.ivMeter, 21);
        sViewsWithIds.put(R.id.vwDividerLine2, 22);
        sViewsWithIds.put(R.id.tvServicePartLabel, 23);
        sViewsWithIds.put(R.id.llServiceHistory, 24);
        sViewsWithIds.put(R.id.rvServiceItems, 25);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityServiceHistoryDetailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 26, sIncludes, sViewsWithIds));
    }
    private ActivityServiceHistoryDetailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[11]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[9]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[19]
            , (android.widget.ImageView) bindings[21]
            , (android.widget.ImageView) bindings[17]
            , (android.widget.ImageView) bindings[12]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[24]
            , (android.widget.LinearLayout) bindings[20]
            , (androidx.recyclerview.widget.RecyclerView) bindings[25]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[14]
            , (android.view.View) bindings[16]
            , (android.view.View) bindings[22]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvDate.setTag(null);
        this.tvJobCardId.setTag(null);
        this.tvMetadata.setTag(null);
        this.tvMeterReading.setTag(null);
        this.tvPageHead.setTag(null);
        this.tvPrice.setTag(null);
        this.tvServiceCenterName.setTag(null);
        this.tvTxHash.setTag(null);
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
            setItem((ymsli.com.cpo.data.model.MaintenanceList) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable ymsli.com.cpo.data.model.MaintenanceList Item) {
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
        ymsli.com.cpo.data.model.MaintenanceList item = mItem;
        java.lang.String itemServiceMetadataIpfsCid = null;
        java.lang.String itemMaintenanceIdToStringSubstringInt24 = null;
        java.lang.String itemMaintenanceIdToString = null;
        java.lang.String itemDealerName = null;
        java.lang.String javaLangStringItemServiceMetadataIpfsCid = null;
        java.lang.String javaLangStringItemTotalMaintenanceCost = null;
        java.lang.String itemTripMeterReading = null;
        java.lang.String itemMaintenanceId = null;
        java.lang.Object itemTxHash = null;
        java.lang.String javaLangStringJobCardItemMaintenanceIdToStringSubstringInt24ToUpperCase = null;
        java.lang.String javaLangStringItemTotalMaintenanceCostJavaLangString = null;
        java.lang.String itemDealerNameJavaLangStringAuthorizedYamahaDealer = null;
        int itemTotalMaintenanceCost = 0;
        java.lang.String itemDate = null;
        java.lang.String javaLangStringItemTxHash = null;
        java.lang.String itemTripMeterReadingJavaLangStringKm = null;
        ymsli.com.cpo.data.model.Dealer itemDealer = null;
        java.lang.String itemMaintenanceIdToStringSubstringInt24ToUpperCase = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.serviceMetadataIpfsCid
                    itemServiceMetadataIpfsCid = item.getServiceMetadataIpfsCid();
                    // read item.tripMeterReading
                    itemTripMeterReading = item.getTripMeterReading();
                    // read item.maintenanceId
                    itemMaintenanceId = item.getMaintenanceId();
                    // read item.txHash
                    itemTxHash = item.getTxHash();
                    // read item.totalMaintenanceCost
                    itemTotalMaintenanceCost = item.getTotalMaintenanceCost();
                    // read item.Date
                    itemDate = item.getDate();
                    // read item.dealer
                    itemDealer = item.getDealer();
                }


                // read ("") + (item.serviceMetadataIpfsCid)
                javaLangStringItemServiceMetadataIpfsCid = ("") + (itemServiceMetadataIpfsCid);
                // read (item.tripMeterReading) + (" km")
                itemTripMeterReadingJavaLangStringKm = (itemTripMeterReading) + (" km");
                // read ("") + (item.txHash)
                javaLangStringItemTxHash = ("") + (itemTxHash);
                // read ("$ ") + (item.totalMaintenanceCost)
                javaLangStringItemTotalMaintenanceCost = ("$ ") + (itemTotalMaintenanceCost);
                if (itemMaintenanceId != null) {
                    // read item.maintenanceId.toString()
                    itemMaintenanceIdToString = itemMaintenanceId.toString();
                }
                if (itemDealer != null) {
                    // read item.dealer.name
                    itemDealerName = itemDealer.getName();
                }


                // read (("$ ") + (item.totalMaintenanceCost)) + ("/-")
                javaLangStringItemTotalMaintenanceCostJavaLangString = (javaLangStringItemTotalMaintenanceCost) + ("/-");
                // read (item.dealer.name) + ("(Authorized Yamaha Dealer)")
                itemDealerNameJavaLangStringAuthorizedYamahaDealer = (itemDealerName) + ("(Authorized Yamaha Dealer)");
                if (itemMaintenanceIdToString != null) {
                    // read item.maintenanceId.toString().substring(24)
                    itemMaintenanceIdToStringSubstringInt24 = itemMaintenanceIdToString.substring(24);
                }


                if (itemMaintenanceIdToStringSubstringInt24 != null) {
                    // read item.maintenanceId.toString().substring(24).toUpperCase()
                    itemMaintenanceIdToStringSubstringInt24ToUpperCase = itemMaintenanceIdToStringSubstringInt24.toUpperCase();
                }


                // read ("JobCard - ") + (item.maintenanceId.toString().substring(24).toUpperCase())
                javaLangStringJobCardItemMaintenanceIdToStringSubstringInt24ToUpperCase = ("JobCard - ") + (itemMaintenanceIdToStringSubstringInt24ToUpperCase);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvDate, itemDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvJobCardId, javaLangStringJobCardItemMaintenanceIdToStringSubstringInt24ToUpperCase);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvMetadata, javaLangStringItemServiceMetadataIpfsCid);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvMeterReading, itemTripMeterReadingJavaLangStringKm);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvPageHead, javaLangStringJobCardItemMaintenanceIdToStringSubstringInt24ToUpperCase);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvPrice, javaLangStringItemTotalMaintenanceCostJavaLangString);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvServiceCenterName, itemDealerNameJavaLangStringAuthorizedYamahaDealer);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTxHash, javaLangStringItemTxHash);
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