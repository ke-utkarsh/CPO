package ymsli.com.cpo.data.model

import java.io.Serializable

/**
 * Project Name : YSUP
 * @company  YMSLI
 * @author   VE00YM491
 * @date     25-11-2022
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
data class GetAppointmentResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: List<AppointmentResult>,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: Any,
    val Version: String
)

data class AppointmentResult(
    val Concerend: List<Concerend>,
    val Date: String,
    val DateInEpoch: Long,
    val Dealer: Dealer,
    val MaintenanceId: Any?,
    val ServiceRequestId: String,
    val Time: String,
    val TripAndMeter: List<TripAndMeter>
)

data class Concerend(
    val LineDesc: String,
    val LineId: String,
    val LineImages: List<Any>,
    val LineType: LineType
)



data class TripAndMeter(
    val LineDesc: String,
    val LineId: String,
    val LineImages: List<LineImage>,
    val LineType: LineType
)

data class LineType(
    val Description: String,
    val Id: Int,
    val Name: String
): Serializable

data class LineImage(
    val Id: String,
    val IpfsUrl: String,
    val OriginalCID: String,
    val OriginalImageUrl: String,
    val ThumbnailUrl: String
) : Serializable