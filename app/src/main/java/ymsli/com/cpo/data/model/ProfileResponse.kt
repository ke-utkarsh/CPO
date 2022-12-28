package ymsli.com.cpo.data.model

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
data class ProfileResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: ProfileData,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: String,
    val Version: String
)

data class ProfileData(
    val Address: String,
    val DOB: String,
    val Email: String,
    val FullName: String,
    val Initials: String,
    val PhoneNumber: String,
    val TxHash: String,
    val WalletAddress: String,
    val WalletAddressUrl: String,
    val TxHashUrl: String,
    val YID: String
)