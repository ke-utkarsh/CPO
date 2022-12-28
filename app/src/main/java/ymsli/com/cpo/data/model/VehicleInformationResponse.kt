package ymsli.com.cpo.data.model

import java.io.Serializable

/**
 * Project Name : YSUP
 * @company  YMSLI
 * @author   VE00YM491
 * @date     15-11-2022
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
data class VehicleInformationResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: VehicleInformationData,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: String,
    val Version: String
)

data class VehicleInformationData(
    val ChassisNo: String,
    val Colour: String,
    val Displacement: String,
    val DisplayImage: String,
    val DisplayImage1: String,
    val EngineNo: String,
    val Model: String,
    val NFTStatus: NFTStatus,
    val PdiImages: List<PdiImage>,
    val TopSpeed: String,
    val VIN: String,
    val VehicleId: String,
    val VehicleMasterId: String,
    val VehiclePhotos: List<VehiclePhoto>,
    val YID: String,
    val Year: String,
    val _0_to_100: String,
    val CustomerName: Any,
    val TxHash: Any?,
    val TxHashUrl: Any?,
    val TripMeterReading: Any?,
    val NftId: Any?
) : Serializable

data class NFTStatus(
    val Description: String,
    val Id: Int,
    val Name: String
): Serializable

data class PdiImage(
    val Id: String,
    val OriginalImageUrl: String,
    val OriginalCID: Any,
    val ThumbnailUrl: String,
    val IpfsUrl: String
): Serializable

data class VehiclePhoto(
    val Id: String,
    val OriginalImageUrl: String,
    val OriginalCID: String,
    val ThumbnailUrl: String,
    val IpfsUrl: String
): Serializable