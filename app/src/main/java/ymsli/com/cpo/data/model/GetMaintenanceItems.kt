package ymsli.com.cpo.data.model

data class GetMaintenanceItems (
    val Environment: String,
    val ErrorMessage: Any,
    var Result: MaintenanceResult,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: Any,
    val Version: String
    )

data class MaintenanceResult(
    val MaintenanceId: String,
    val AppointmentId: String,
    val MaitenanceLines: Any,
    val TxHash: Any,
    val TxHashUrl: Any,
    val ServiceMetadataIpfsCid: Any,
    val ServiceMetadataUrl: Any,
    var Lines:List<LineResult>,
    val Others:List<LineResult>
)
data class LineResult(
    var LineId:String,
    var LineDesc:String,
    var LineItem:LineItemResult,
    var LineImages:ArrayList<PdiImageData>,

    )
data class LineItemResult(
    val Id:Int,
    val Name:String,
    val Description: String
)