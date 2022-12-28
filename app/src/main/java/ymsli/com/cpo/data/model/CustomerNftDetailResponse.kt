package ymsli.com.cpo.data.model

/**
 * Project Name : YSUP
 * @company  YMSLI
 * @author   VE00YM491
 * @date     19-11-2022
 * Copyright (c) 2021, Yamaha Motor Solutions (INDIA) Pvt Ltd.
 * Description
 * -----------------------------------------------------------------------------------
 * YSUPApp :
 * -----------------------------------------------------------------------------------
 * Revision History
 * -----------------------------------------------------------------------------------
 * Modified By          Modified On         Description
 * -----------------------------------------------------------------------------------
 */
data class CustomerNftDetailResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: List<NftDetail>,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: String,
    val Version: String
)

data class NftDetail(
    val Colour: String,
    val DisplayImage: String,
    val Model: String,
    val NFTId: String,
    val NFTMetadataIpfsCid: String,
    val NFTMetadataUrl: String,
    val NFTStatus: CustomerNFTStatus,
    val NftMintDate: String,
    val VehicleId: String,
    val VehicleMasterId: String,
    val TxHashUrl: String,
    val TxHash : String
)

data class CustomerNFTStatus(
    val Description: String,
    val Id: Int,
    val Name: String
)