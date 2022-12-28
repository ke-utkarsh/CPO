package ymsli.com.cpo.data.model

data class GetNftStatsResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: NftStats,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: Any,
    val Version: String
)

data class NftStats(
    val MintableNFTCount: Int,
    val MintedNFTCount: Int
)