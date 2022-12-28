package ymsli.com.cpo.data.model

data class SubmitCompleteResponseModel(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: Result,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: String,
    val Version: String
)

data class Result(
    val TxHash: String,
    val TxHashUrl: String
)
