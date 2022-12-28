package ymsli.com.cpo.data.model

data class AcceptApiResponse (
    val Environment: String,
    val ErrorMessage: Any,
    val Result: AcceptResult,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: Any,
    val Version: String
    )

data class AcceptResult (
    val TxHash: Any?,
    val TxHashUrl: Any?
    )
