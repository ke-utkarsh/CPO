package ymsli.com.cpo.data.model

data class GetBeforeImagesModel(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: GetBeforeImagesIssueList,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: String,
    val Version: String
)

data class GetBeforeImagesIssueList(
    val Concerend: List<Concerned>,
    val Date: String,
    val DateInEpoch: Long,
    val Dealer: Dealer,
    val ServiceRequestId: String,
    val Time: String,
    val TripAndMeter: List<TripAndMeter>,
    val MaintenanceConcerned : List<Concerned>,
    val MaintenanceId : String,
    val VehicleId : String
)

data class Concerned(
    val LineDesc: String,
    val LineId: String,
    val LineImages: ArrayList<LineImage>,
    val LineType: LineType
)

