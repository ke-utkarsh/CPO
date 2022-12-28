package ymsli.com.cpo.data.model

data class WalletLinkResponse (
    val Environment: String,
    val ErrorMessage: Any,
    val Result: Any,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: String,
    val Version: String
)

