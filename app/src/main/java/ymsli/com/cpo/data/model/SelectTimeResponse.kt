package ymsli.com.cpo.data.model

/**
 * Project Name : YSUP
 * @company  YMSLI
 * @author   VE00YM491
 * @date     22-11-2022
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
data class SelectTimeResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: List<String>,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: Any,
    val Version: String
)