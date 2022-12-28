package ymsli.com.cpo.data.model

data class OfferListResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: List<OfferList>,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: Any,
    val Version: String
)

data class OfferList(
    val Address: String,
    val Email: String,
    val Name: String,
    val OfferId: String,
    val OfferPrice: Int,
    val PhoneNumber: String,
    val UserId: String,
    val VehicleId: String,
    val Yid: String,
    val SendTranscationParams: TransactionParams
)

data class TransactionParams(
    val OwnerWallet: String,
    val OfferWallet: String,
    val SmartContractAddress: String,
    val TransactionGasLimit: Int,
    val EncodedData: String
)