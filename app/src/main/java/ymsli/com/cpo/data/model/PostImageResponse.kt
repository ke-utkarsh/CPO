package ymsli.com.cpo.data.model

data class PostImageResponse(
    val Environment: String,
    val ErrorMessage: String,
    val Result: PdiImageData,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: String,
    val Version: String
)

data class PdiImageData(
    val Id: String,
    val OriginalCID: String,
    val OriginalImageUrl: String,
    val ThumbnailUrl: String,
    val IpfsUrl :String
)