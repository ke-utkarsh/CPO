package ymsli.com.cpo.data.model

/**
 * Project Name : YSUP
 * @company  YMSLI
 * @author   VE00YM491
 * @date     11-11-2022
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
data class UserResponse(
    val Environment: String,
    val StatusCode : Int,
    val ErrorMessage: Any,
    val Result: List<UserModel>,
    val Status: Boolean,
    val SuccessMessage: String,
    val Version: String
)

data class UserModel(
    val FullName: String,
    val Id: String,
    val UserName: String,
    val YID: String
)
{
    override fun toString(): String {
        return "$YID $FullName"
    }
}