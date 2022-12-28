package ymsli.com.cpo.data.model

import java.io.Serializable


data class GetMaintenanceListResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: List<MaintenanceList>,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: Any,
    val Version: String
)

data class MaintenanceList(
    val AppointmentId: String,
    val Concerend: List<MaintenanceConcerned>,
    val Date: String,
    val DateInEpoch: Long,
    val Dealer: Dealer,
    val Lines: List<MaitenanceLine>,
    val MaintenanceConcerned: List<MaintenanceConcerned>,
    val MaintenanceId: String,
    val MaitenanceLines: List<MaitenanceLine>,
    val Others: List<MaitenanceLine>,
    val Time: String,
    val TotalMaintenanceCost: Int,
    val TripMeterReading: String,
    val TxHash: Any,
    val TxHashUrl: Any,
    val VehicleId: String,
    val ServiceMetadataIpfsCid: String?,
    val ServiceMetadataUrl: String
): Serializable

data class Dealer(
    val Address: String,
    val Distance: Int,
    val DistanceScale: String,
    val Email: String,
    val Id: String,
    val Lat: String,
    val Long: String,
    val Name: String,
    val Phone: String,
    val PlusCode: String
) : Serializable

data class MaintenanceConcerned(
    val LineDesc: String,
    val LineId: String,
    val LineImages: List<LineImage>,
    val LineType: LineType
) : Serializable

data class MaitenanceLine(
    val Serial: String,
    val LineDesc: String,
    val LineId: String,
    val LineImages: List<LineImage>,
    val LineItem: LineItem
) : Serializable



data class LineItem(
    val Description: String,
    val Id: Int,
    val Name: String
) : Serializable




