package ymsli.com.cpo.data.api

object RequestBodies {

    data class LoginRequestBody(
        val UserName: String,
        val Role: String,

        )

    data class UserRequestBody(
        val RoleId: String
    )

    data class VehicleInformationBody(
        val VehicleId: String
    )

    data class WalletLinkRequestBody(
        val WalletAddress: String
    )

    data class SubmitNftDataBody(
        val VehicleMasterId: String,
        val PDIImages: List<String>,
        val VehiclePhotos: List<String>
    )

    data class CustomerNftDetailRequestBody(
        val NftStatus: Int
    )

    data class MintNftRequestBody(
        val VehicleId: String
    )

    data class SubmitAppointRequestBody(
        val AppointmentId: String,
        val ConcernLines: List<LineItemRequest>,
        val Date: String,
        val DealerId: String,
        val TimeSlot: String,
        val TripAndMeterLine: LineItemRequest,
        val VehicleId: String,

    )

    data class GetAppointmentRequestBody(
        val VehicleId : String
    )

    data class GetMaintenanceRequestBody(
        val MaintenanceId: String
    )
    data class GetAppointmentDetail(
        val AppointmentId: String
    )

    data class SaveMaintenanceRequestBody(
        val MaintenanceId: String,
        val MaintenanceLines: List<LineItemRequest>
    )
     data class LineItemRequest(
         val LineId:String,
         val LineDesc:String,
         val LineImages:List<String>
     )

    data class SubmitCompleteRequest(
        val IsMaintenanceCompleted: Boolean,
        val MaintenanceId: String,
        val MaintenanceLinesConcerns: List<MaintenanceLinesConcern>,
        val TotalMaintenanceCost: Int,
        val VehicleId: String
    )

    data class MaintenanceLinesConcern(
        val LineId: String,
        val LineImages: List<String>
    )

    data class SubmitOfferPriceRequest (
        val VehicleId: String,
        val Price: Int
    )

    data class OfferRejectRequest(
        val VehicleId: String,
        val OfferId: String
    )

    data class OfferAcceptedRequest(
        val VehicleId: String,
        val OfferId: String,
        val TransferTxHash: String
    )

}