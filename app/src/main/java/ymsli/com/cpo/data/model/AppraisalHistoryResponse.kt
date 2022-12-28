package ymsli.com.cpo.data.model

import androidx.annotation.NonNull
import java.io.Serializable

data class AppraisalHistoryResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: List<AppraisalHistoryResult>,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: Any,
    val Version: String
)

data class AppraisalHistoryResult(
    val AppraisalDetailModel: AppraisalDetailModel,
    val Date: String,
    val Grade: String,
    val Point: Double,
    val TripMeterReading: String,
    val VehicleCondition: String
)

data class AppraisalDetailModel(
    @NonNull
    val AppraisalSummaryUrl: String,
    @NonNull
    val ApplicableOfKeyNumberPlate: Boolean,
    @NonNull
    val AppraisalDate: String,
    @NonNull
    val AppraisalGradeList: List<AppraisalGrade>,
    @NonNull
    val Appraiser: String,
    @NonNull
    val BodyEvaluation: BodyEvaluation,
    @NonNull
    val Chassis: String,
    @NonNull
    val ChassisEvaluation: ChassisEvaluation,
    @NonNull
    val CustomerName: String,
    @NonNull
    val Dealer: String,
    @NonNull
    val ElectricalEvaluation: ElectricalEvaluation,
    @NonNull
    val EngineEvaluation: EngineEvaluation,
    val EngineNo: String,
    @NonNull
    val ExteriorEvaluation: ExteriorEvaluation,
    val Grade: String,
    val LicensePlateNumber: String,
    val MaintenanceBook: Boolean,
    @NonNull
    val MaintenanceHistoryEvaluation: MaintenanceHistoryEvaluation,
    @NonNull
    val Mileage: String,
    val ModelName: String,
    val ModelYear: String,
    @NonNull
    val OwnerCertification: Boolean,
    @NonNull
    val OwnerManual: Boolean,
    val OwnereshipOfKeyLicensePlates: Boolean,
    val PersonInCharge: String,
    val Point: Double,
    val SpareKeyQuantity: Int,
    val WheelTire: String
) : Serializable

data class AppraisalGrade(
    val Grade: String,
    val Range: String,
    val VehicleCondition: String
) : Serializable

data class BodyEvaluation(
    val Frame: String,
    val Stand: String,
    val Step: Int
) : Serializable

data class ChassisEvaluation(
    val BrakeSystem: String,
    val CVT: String,
    val FrontForkOrRearShock: String,
    val Handle: String,
    val Stem: String,
    val WheelTire: String
) : Serializable

data class ElectricalEvaluation(
    val Lights: String,
    val Meter: String,
    val StartingChargeSystem: String
) : Serializable

data class EngineEvaluation(
    val Apperance: String,
    val CabFI: String,
    val CoolingSystem: String,
    val Engine: String
) : Serializable

data class ExteriorEvaluation(
    val Cowl: String,
    val Fender: String,
    val Mirror: String,
    val Muffler: String,
    val Seat: String,
    val Tank: String
) : Serializable

data class MaintenanceHistoryEvaluation(
    val FifthYear: Int,
    val FourthYear: Int,
    val SecondYear: Int,
    val SeventhYear: Int,
    val SixthYear: Int,
    val ThirdYear: Int,
    val WarrantyPeriod: Int
) : Serializable