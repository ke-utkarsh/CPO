package ymsli.com.cpo.data.model

/**
 * Project Name : YSUP
 * @company  YMSLI
 * @author   VE00YM491
 * @date     23-11-2022
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
data class ConcernedIssueModel(
    var issueDetail: String,
    val LineId: String,
    var issueImages: ArrayList<ConcernedImagesModel>
)

data class ConcernedImagesModel(
    val Id: String,
    val OriginalImageUrl: String,
    val ThumbnailUrl: String,
    val IpfsUrl: String
    )

data class TripMeterImagesModel(
    val Id: String,
    val OriginalImageUrl: String,
    val ThumbnailUrl: String,
    val IpfsUrl: String
    )


data class ConcernedIssueRequestModel(
    var LineDesc:String,
    var itemId:Int,
    var LineId:String,
    var LineImages : ArrayList<String>
)

