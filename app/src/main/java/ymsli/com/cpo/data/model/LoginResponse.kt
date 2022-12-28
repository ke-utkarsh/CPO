package ymsli.com.cpo.data.model

data class LoginResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: String,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: String,
    val Version: String
)