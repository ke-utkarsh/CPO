package ymsli.com.cpo.data.model

import java.io.Serializable

data class DealerNearResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: List<DealersResult>,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: Any,
    val Version: String
)

data class DealersResult(
    val Id:String,
    val Name:String,
    val Distance:Int,
    val DistanceScale:String,
    val Email:String,
    val Phone:String,
    val Address:String,
    val Lat:String,
    val Long:String,
    val PlusCode:String
) : Serializable
