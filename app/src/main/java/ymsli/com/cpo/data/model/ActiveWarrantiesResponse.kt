package ymsli.com.cpo.data.model

data class ActiveWarrantiesResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: List<ActiveWarrantiesData>,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: Any,
    val Version: String
)

data class ActiveWarrantiesData(
    val ChassisNo: String,
    val Colour: String,
    val EngineNo: String,
    val Model: String,
    val NFTStatus: String,
    val VIN: String,
    val VechileId: String,
    val Year: String,
    val DisplayImage: String,
    val CustomerName: String,
    val Date: String
)