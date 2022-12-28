package ymsli.com.cpo.data.model

/**
 * Project Name : YSUP
 * @company  YMSLI
 * @author   VE00YM491
 * @date     10-11-2022
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
data class RoleResponse(
    val Environment: String,
    val StatusCode : Int,
    val ErrorMessage: Any,
    val Result: List<RoleModel>,
    val Status: Boolean,
    val SuccessMessage: String,
    val Version: String
)

data class RoleModel(
    val Id: String,
    val Name: String
)

{
    override fun toString(): String = Name
}