package ymsli.com.cpo.databinding;
import ymsli.com.cpo.R;
import ymsli.com.cpo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityAppraisalDetailBindingImpl extends ActivityAppraisalDetailBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.clToolbar, 43);
        sViewsWithIds.put(R.id.ivBack, 44);
        sViewsWithIds.put(R.id.tvPageHead, 45);
        sViewsWithIds.put(R.id.clHeader, 46);
        sViewsWithIds.put(R.id.svlSpeed, 47);
        sViewsWithIds.put(R.id.llAppraisalGradeListHead, 48);
        sViewsWithIds.put(R.id.rvAppraisalGradeList, 49);
        sViewsWithIds.put(R.id.llTabs, 50);
        sViewsWithIds.put(R.id.clSummary, 51);
        sViewsWithIds.put(R.id.tvSummary, 52);
        sViewsWithIds.put(R.id.clEvaluation, 53);
        sViewsWithIds.put(R.id.tvEvaluation, 54);
        sViewsWithIds.put(R.id.ivDownload, 55);
        sViewsWithIds.put(R.id.svSummary, 56);
        sViewsWithIds.put(R.id.cvFirst, 57);
        sViewsWithIds.put(R.id.cvSecond, 58);
        sViewsWithIds.put(R.id.ivVehicleImage, 59);
        sViewsWithIds.put(R.id.pbProgressBar, 60);
        sViewsWithIds.put(R.id.tvApplicableOfKeyNumberPlate, 61);
        sViewsWithIds.put(R.id.tvOwnereshipOfKeyLicensePlates, 62);
        sViewsWithIds.put(R.id.tvOwnerCertification, 63);
        sViewsWithIds.put(R.id.tvMaintenanceBook, 64);
        sViewsWithIds.put(R.id.tvOwnerManual, 65);
        sViewsWithIds.put(R.id.svEvaluation, 66);
        sViewsWithIds.put(R.id.tvEngineHeading, 67);
        sViewsWithIds.put(R.id.cvFirst1, 68);
        sViewsWithIds.put(R.id.tvChassisHeading, 69);
        sViewsWithIds.put(R.id.cvSecond1, 70);
        sViewsWithIds.put(R.id.tvBodyHeading, 71);
        sViewsWithIds.put(R.id.cvThird1, 72);
        sViewsWithIds.put(R.id.tvElectricalHeading, 73);
        sViewsWithIds.put(R.id.cvFourth1, 74);
        sViewsWithIds.put(R.id.tvExteriorHeading, 75);
        sViewsWithIds.put(R.id.cvFifth1, 76);
        sViewsWithIds.put(R.id.tvMaintenanceHistory, 77);
        sViewsWithIds.put(R.id.cvSixth1, 78);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityAppraisalDetailBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 79, sIncludes, sViewsWithIds));
    }
    private ActivityAppraisalDetailBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[53]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[46]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[51]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[43]
            , (androidx.cardview.widget.CardView) bindings[76]
            , (androidx.cardview.widget.CardView) bindings[57]
            , (androidx.cardview.widget.CardView) bindings[68]
            , (androidx.cardview.widget.CardView) bindings[74]
            , (androidx.cardview.widget.CardView) bindings[58]
            , (androidx.cardview.widget.CardView) bindings[70]
            , (androidx.cardview.widget.CardView) bindings[78]
            , (androidx.cardview.widget.CardView) bindings[72]
            , (android.widget.ImageView) bindings[44]
            , (android.widget.ImageView) bindings[55]
            , (android.widget.ImageView) bindings[59]
            , (android.widget.LinearLayout) bindings[48]
            , (android.widget.LinearLayout) bindings[50]
            , (android.widget.ProgressBar) bindings[60]
            , (androidx.recyclerview.widget.RecyclerView) bindings[49]
            , (android.widget.ScrollView) bindings[66]
            , (android.widget.ScrollView) bindings[56]
            , (com.github.anastr.speedviewlib.SpeedView) bindings[47]
            , (android.widget.TextView) bindings[61]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[71]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[69]
            , (android.widget.TextView) bindings[30]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[73]
            , (android.widget.TextView) bindings[67]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[54]
            , (android.widget.TextView) bindings[75]
            , (android.widget.TextView) bindings[33]
            , (android.widget.TextView) bindings[40]
            , (android.widget.TextView) bindings[39]
            , (android.widget.TextView) bindings[24]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[29]
            , (android.widget.TextView) bindings[64]
            , (android.widget.TextView) bindings[77]
            , (android.widget.TextView) bindings[27]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[34]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[35]
            , (android.widget.TextView) bindings[63]
            , (android.widget.TextView) bindings[65]
            , (android.widget.TextView) bindings[62]
            , (android.widget.TextView) bindings[45]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[32]
            , (android.widget.TextView) bindings[37]
            , (android.widget.TextView) bindings[42]
            , (android.widget.TextView) bindings[41]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[28]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[26]
            , (android.widget.TextView) bindings[52]
            , (android.widget.TextView) bindings[31]
            , (android.widget.TextView) bindings[38]
            , (android.widget.TextView) bindings[36]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[11]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvAppraisalDate.setTag(null);
        this.tvAppraiser.setTag(null);
        this.tvBrakeSystem.setTag(null);
        this.tvCVT.setTag(null);
        this.tvChassis.setTag(null);
        this.tvCowl.setTag(null);
        this.tvCustomerName.setTag(null);
        this.tvDealer.setTag(null);
        this.tvE1.setTag(null);
        this.tvE2.setTag(null);
        this.tvE3.setTag(null);
        this.tvE4.setTag(null);
        this.tvEngineNumber.setTag(null);
        this.tvFender.setTag(null);
        this.tvFifthYear.setTag(null);
        this.tvFourthYear.setTag(null);
        this.tvFrame.setTag(null);
        this.tvFrontForkOrRearShock.setTag(null);
        this.tvHandle.setTag(null);
        this.tvLicencePlateNumber.setTag(null);
        this.tvLights.setTag(null);
        this.tvMeter.setTag(null);
        this.tvMileage.setTag(null);
        this.tvMirror.setTag(null);
        this.tvModelName.setTag(null);
        this.tvModelYear.setTag(null);
        this.tvMuffler.setTag(null);
        this.tvPersonInCharge.setTag(null);
        this.tvSeat.setTag(null);
        this.tvSecondYear.setTag(null);
        this.tvSeventhYear.setTag(null);
        this.tvSixthYear.setTag(null);
        this.tvSpareKey.setTag(null);
        this.tvStand.setTag(null);
        this.tvStartingChargeSystem.setTag(null);
        this.tvStem.setTag(null);
        this.tvStep.setTag(null);
        this.tvTank.setTag(null);
        this.tvThirdYear.setTag(null);
        this.tvWarrantyPeriod.setTag(null);
        this.tvWheelOrTire.setTag(null);
        this.tvWheelTire.setTag(null);
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
            setItem((ymsli.com.cpo.data.model.AppraisalDetailModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable ymsli.com.cpo.data.model.AppraisalDetailModel Item) {
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
        int itemMaintenanceHistoryEvaluationFifthYear = 0;
        ymsli.com.cpo.data.model.AppraisalDetailModel item = mItem;
        java.lang.String itemEngineEvaluationApperance = null;
        java.lang.String itemPersonInCharge = null;
        java.lang.String itemBodyEvaluationStand = null;
        int itemMaintenanceHistoryEvaluationWarrantyPeriod = 0;
        java.lang.String itemChassisEvaluationFrontForkOrRearShock = null;
        ymsli.com.cpo.data.model.ExteriorEvaluation itemExteriorEvaluation = null;
        int itemMaintenanceHistoryEvaluationThirdYear = 0;
        java.lang.String itemModelName = null;
        java.lang.String itemChassisEvaluationWheelTire = null;
        java.lang.String javaLangStringItemMaintenanceHistoryEvaluationSixthYear = null;
        java.lang.String itemExteriorEvaluationFender = null;
        java.lang.String javaLangStringItemMaintenanceHistoryEvaluationFourthYear = null;
        int itemSpareKeyQuantity = 0;
        java.lang.String itemExteriorEvaluationCowl = null;
        ymsli.com.cpo.data.model.ElectricalEvaluation itemElectricalEvaluation = null;
        java.lang.String itemMileage = null;
        java.lang.String javaLangStringItemElectricalEvaluationLights = null;
        java.lang.String itemChassisEvaluationBrakeSystem = null;
        java.lang.String itemChassis = null;
        ymsli.com.cpo.data.model.EngineEvaluation itemEngineEvaluation = null;
        java.lang.String javaLangStringItemBodyEvaluationStep = null;
        java.lang.String javaLangStringItemExteriorEvaluationMuffler = null;
        java.lang.String itemEngineEvaluationEngine = null;
        java.lang.String javaLangStringItemMaintenanceHistoryEvaluationSeventhYear = null;
        java.lang.String javaLangStringItemExteriorEvaluationFender = null;
        java.lang.String itemExteriorEvaluationMirror = null;
        java.lang.String itemEngineNo = null;
        java.lang.String itemEngineEvaluationCoolingSystem = null;
        java.lang.String itemModelYear = null;
        java.lang.String itemExteriorEvaluationMuffler = null;
        java.lang.String itemDealer = null;
        java.lang.String itemWheelTire = null;
        ymsli.com.cpo.data.model.ChassisEvaluation itemChassisEvaluation = null;
        java.lang.String itemElectricalEvaluationStartingChargeSystem = null;
        java.lang.String javaLangStringItemMaintenanceHistoryEvaluationWarrantyPeriod = null;
        java.lang.String javaLangStringItemMaintenanceHistoryEvaluationThirdYear = null;
        int itemMaintenanceHistoryEvaluationSixthYear = 0;
        java.lang.String itemElectricalEvaluationLights = null;
        java.lang.String javaLangStringItemSpareKeyQuantity = null;
        ymsli.com.cpo.data.model.MaintenanceHistoryEvaluation itemMaintenanceHistoryEvaluation = null;
        java.lang.String javaLangStringItemMaintenanceHistoryEvaluationSecondYear = null;
        java.lang.String itemExteriorEvaluationSeat = null;
        ymsli.com.cpo.data.model.BodyEvaluation itemBodyEvaluation = null;
        java.lang.String javaLangStringItemExteriorEvaluationMirror = null;
        java.lang.String itemElectricalEvaluationMeter = null;
        java.lang.String itemChassisEvaluationHandle = null;
        java.lang.String itemCustomerName = null;
        java.lang.String itemChassisEvaluationStem = null;
        int itemMaintenanceHistoryEvaluationFourthYear = 0;
        java.lang.String itemEngineEvaluationCabFI = null;
        java.lang.String itemBodyEvaluationFrame = null;
        int itemMaintenanceHistoryEvaluationSeventhYear = 0;
        int itemBodyEvaluationStep = 0;
        java.lang.String itemAppraisalDate = null;
        java.lang.String itemLicensePlateNumber = null;
        java.lang.String javaLangStringItemExteriorEvaluationSeat = null;
        java.lang.String javaLangStringItemMaintenanceHistoryEvaluationFifthYear = null;
        int itemMaintenanceHistoryEvaluationSecondYear = 0;
        java.lang.String itemExteriorEvaluationTank = null;
        java.lang.String itemAppraiser = null;
        java.lang.String itemChassisEvaluationCVT = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (item != null) {
                    // read item.personInCharge
                    itemPersonInCharge = item.getPersonInCharge();
                    // read item.exteriorEvaluation
                    itemExteriorEvaluation = item.getExteriorEvaluation();
                    // read item.modelName
                    itemModelName = item.getModelName();
                    // read item.spareKeyQuantity
                    itemSpareKeyQuantity = item.getSpareKeyQuantity();
                    // read item.electricalEvaluation
                    itemElectricalEvaluation = item.getElectricalEvaluation();
                    // read item.mileage
                    itemMileage = item.getMileage();
                    // read item.chassis
                    itemChassis = item.getChassis();
                    // read item.engineEvaluation
                    itemEngineEvaluation = item.getEngineEvaluation();
                    // read item.engineNo
                    itemEngineNo = item.getEngineNo();
                    // read item.modelYear
                    itemModelYear = item.getModelYear();
                    // read item.dealer
                    itemDealer = item.getDealer();
                    // read item.wheelTire
                    itemWheelTire = item.getWheelTire();
                    // read item.chassisEvaluation
                    itemChassisEvaluation = item.getChassisEvaluation();
                    // read item.maintenanceHistoryEvaluation
                    itemMaintenanceHistoryEvaluation = item.getMaintenanceHistoryEvaluation();
                    // read item.bodyEvaluation
                    itemBodyEvaluation = item.getBodyEvaluation();
                    // read item.customerName
                    itemCustomerName = item.getCustomerName();
                    // read item.appraisalDate
                    itemAppraisalDate = item.getAppraisalDate();
                    // read item.licensePlateNumber
                    itemLicensePlateNumber = item.getLicensePlateNumber();
                    // read item.appraiser
                    itemAppraiser = item.getAppraiser();
                }


                if (itemExteriorEvaluation != null) {
                    // read item.exteriorEvaluation.Fender
                    itemExteriorEvaluationFender = itemExteriorEvaluation.getFender();
                    // read item.exteriorEvaluation.Cowl
                    itemExteriorEvaluationCowl = itemExteriorEvaluation.getCowl();
                    // read item.exteriorEvaluation.Mirror
                    itemExteriorEvaluationMirror = itemExteriorEvaluation.getMirror();
                    // read item.exteriorEvaluation.Muffler
                    itemExteriorEvaluationMuffler = itemExteriorEvaluation.getMuffler();
                    // read item.exteriorEvaluation.Seat
                    itemExteriorEvaluationSeat = itemExteriorEvaluation.getSeat();
                    // read item.exteriorEvaluation.Tank
                    itemExteriorEvaluationTank = itemExteriorEvaluation.getTank();
                }
                // read ("") + (item.spareKeyQuantity)
                javaLangStringItemSpareKeyQuantity = ("") + (itemSpareKeyQuantity);
                if (itemElectricalEvaluation != null) {
                    // read item.electricalEvaluation.StartingChargeSystem
                    itemElectricalEvaluationStartingChargeSystem = itemElectricalEvaluation.getStartingChargeSystem();
                    // read item.electricalEvaluation.Lights
                    itemElectricalEvaluationLights = itemElectricalEvaluation.getLights();
                    // read item.electricalEvaluation.Meter
                    itemElectricalEvaluationMeter = itemElectricalEvaluation.getMeter();
                }
                if (itemEngineEvaluation != null) {
                    // read item.engineEvaluation.apperance
                    itemEngineEvaluationApperance = itemEngineEvaluation.getApperance();
                    // read item.engineEvaluation.engine
                    itemEngineEvaluationEngine = itemEngineEvaluation.getEngine();
                    // read item.engineEvaluation.coolingSystem
                    itemEngineEvaluationCoolingSystem = itemEngineEvaluation.getCoolingSystem();
                    // read item.engineEvaluation.cabFI
                    itemEngineEvaluationCabFI = itemEngineEvaluation.getCabFI();
                }
                if (itemChassisEvaluation != null) {
                    // read item.chassisEvaluation.FrontForkOrRearShock
                    itemChassisEvaluationFrontForkOrRearShock = itemChassisEvaluation.getFrontForkOrRearShock();
                    // read item.chassisEvaluation.WheelTire
                    itemChassisEvaluationWheelTire = itemChassisEvaluation.getWheelTire();
                    // read item.chassisEvaluation.BrakeSystem
                    itemChassisEvaluationBrakeSystem = itemChassisEvaluation.getBrakeSystem();
                    // read item.chassisEvaluation.Handle
                    itemChassisEvaluationHandle = itemChassisEvaluation.getHandle();
                    // read item.chassisEvaluation.Stem
                    itemChassisEvaluationStem = itemChassisEvaluation.getStem();
                    // read item.chassisEvaluation.CVT
                    itemChassisEvaluationCVT = itemChassisEvaluation.getCVT();
                }
                if (itemMaintenanceHistoryEvaluation != null) {
                    // read item.maintenanceHistoryEvaluation.FifthYear
                    itemMaintenanceHistoryEvaluationFifthYear = itemMaintenanceHistoryEvaluation.getFifthYear();
                    // read item.maintenanceHistoryEvaluation.WarrantyPeriod
                    itemMaintenanceHistoryEvaluationWarrantyPeriod = itemMaintenanceHistoryEvaluation.getWarrantyPeriod();
                    // read item.maintenanceHistoryEvaluation.ThirdYear
                    itemMaintenanceHistoryEvaluationThirdYear = itemMaintenanceHistoryEvaluation.getThirdYear();
                    // read item.maintenanceHistoryEvaluation.SixthYear
                    itemMaintenanceHistoryEvaluationSixthYear = itemMaintenanceHistoryEvaluation.getSixthYear();
                    // read item.maintenanceHistoryEvaluation.FourthYear
                    itemMaintenanceHistoryEvaluationFourthYear = itemMaintenanceHistoryEvaluation.getFourthYear();
                    // read item.maintenanceHistoryEvaluation.SeventhYear
                    itemMaintenanceHistoryEvaluationSeventhYear = itemMaintenanceHistoryEvaluation.getSeventhYear();
                    // read item.maintenanceHistoryEvaluation.SecondYear
                    itemMaintenanceHistoryEvaluationSecondYear = itemMaintenanceHistoryEvaluation.getSecondYear();
                }
                if (itemBodyEvaluation != null) {
                    // read item.bodyEvaluation.Stand
                    itemBodyEvaluationStand = itemBodyEvaluation.getStand();
                    // read item.bodyEvaluation.frame
                    itemBodyEvaluationFrame = itemBodyEvaluation.getFrame();
                    // read item.bodyEvaluation.Step
                    itemBodyEvaluationStep = itemBodyEvaluation.getStep();
                }


                // read ("") + (item.exteriorEvaluation.Fender)
                javaLangStringItemExteriorEvaluationFender = ("") + (itemExteriorEvaluationFender);
                // read ("") + (item.exteriorEvaluation.Mirror)
                javaLangStringItemExteriorEvaluationMirror = ("") + (itemExteriorEvaluationMirror);
                // read ("") + (item.exteriorEvaluation.Muffler)
                javaLangStringItemExteriorEvaluationMuffler = ("") + (itemExteriorEvaluationMuffler);
                // read ("") + (item.exteriorEvaluation.Seat)
                javaLangStringItemExteriorEvaluationSeat = ("") + (itemExteriorEvaluationSeat);
                // read ("") + (item.electricalEvaluation.Lights)
                javaLangStringItemElectricalEvaluationLights = ("") + (itemElectricalEvaluationLights);
                // read ("") + (item.maintenanceHistoryEvaluation.FifthYear)
                javaLangStringItemMaintenanceHistoryEvaluationFifthYear = ("") + (itemMaintenanceHistoryEvaluationFifthYear);
                // read ("") + (item.maintenanceHistoryEvaluation.WarrantyPeriod)
                javaLangStringItemMaintenanceHistoryEvaluationWarrantyPeriod = ("") + (itemMaintenanceHistoryEvaluationWarrantyPeriod);
                // read ("") + (item.maintenanceHistoryEvaluation.ThirdYear)
                javaLangStringItemMaintenanceHistoryEvaluationThirdYear = ("") + (itemMaintenanceHistoryEvaluationThirdYear);
                // read ("") + (item.maintenanceHistoryEvaluation.SixthYear)
                javaLangStringItemMaintenanceHistoryEvaluationSixthYear = ("") + (itemMaintenanceHistoryEvaluationSixthYear);
                // read ("") + (item.maintenanceHistoryEvaluation.FourthYear)
                javaLangStringItemMaintenanceHistoryEvaluationFourthYear = ("") + (itemMaintenanceHistoryEvaluationFourthYear);
                // read ("") + (item.maintenanceHistoryEvaluation.SeventhYear)
                javaLangStringItemMaintenanceHistoryEvaluationSeventhYear = ("") + (itemMaintenanceHistoryEvaluationSeventhYear);
                // read ("") + (item.maintenanceHistoryEvaluation.SecondYear)
                javaLangStringItemMaintenanceHistoryEvaluationSecondYear = ("") + (itemMaintenanceHistoryEvaluationSecondYear);
                // read ("") + (item.bodyEvaluation.Step)
                javaLangStringItemBodyEvaluationStep = ("") + (itemBodyEvaluationStep);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvAppraisalDate, itemAppraisalDate);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvAppraiser, itemAppraiser);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvBrakeSystem, itemChassisEvaluationBrakeSystem);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCVT, itemChassisEvaluationCVT);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvChassis, itemChassis);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCowl, itemExteriorEvaluationCowl);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvCustomerName, itemCustomerName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvDealer, itemDealer);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvE1, itemEngineEvaluationEngine);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvE2, itemEngineEvaluationCabFI);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvE3, itemEngineEvaluationApperance);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvE4, itemEngineEvaluationCoolingSystem);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvEngineNumber, itemEngineNo);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvFender, javaLangStringItemExteriorEvaluationFender);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvFifthYear, javaLangStringItemMaintenanceHistoryEvaluationFifthYear);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvFourthYear, javaLangStringItemMaintenanceHistoryEvaluationFourthYear);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvFrame, itemBodyEvaluationFrame);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvFrontForkOrRearShock, itemChassisEvaluationFrontForkOrRearShock);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvHandle, itemChassisEvaluationHandle);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvLicencePlateNumber, itemLicensePlateNumber);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvLights, javaLangStringItemElectricalEvaluationLights);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvMeter, itemElectricalEvaluationMeter);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvMileage, itemMileage);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvMirror, javaLangStringItemExteriorEvaluationMirror);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvModelName, itemModelName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvModelYear, itemModelYear);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvMuffler, javaLangStringItemExteriorEvaluationMuffler);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvPersonInCharge, itemPersonInCharge);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvSeat, javaLangStringItemExteriorEvaluationSeat);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvSecondYear, javaLangStringItemMaintenanceHistoryEvaluationSecondYear);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvSeventhYear, javaLangStringItemMaintenanceHistoryEvaluationSeventhYear);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvSixthYear, javaLangStringItemMaintenanceHistoryEvaluationSixthYear);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvSpareKey, javaLangStringItemSpareKeyQuantity);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvStand, itemBodyEvaluationStand);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvStartingChargeSystem, itemElectricalEvaluationStartingChargeSystem);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvStem, itemChassisEvaluationStem);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvStep, javaLangStringItemBodyEvaluationStep);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTank, itemExteriorEvaluationTank);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvThirdYear, javaLangStringItemMaintenanceHistoryEvaluationThirdYear);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvWarrantyPeriod, javaLangStringItemMaintenanceHistoryEvaluationWarrantyPeriod);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvWheelOrTire, itemChassisEvaluationWheelTire);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvWheelTire, itemWheelTire);
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