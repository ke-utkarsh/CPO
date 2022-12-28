package com.ymsli.cotb.utils.motiontoast

/**
 * @Author: Atul Kumar
 * @Created By: VE00YM430
 * @Date: 02-08-2022
 * @Description:
 */

enum class MotionToastStyle {
    SUCCESS, ERROR, WARNING, INFO, DELETE, NO_INTERNET;

    fun getName(): String {
        if (this.name.contains("_")) {
            return this.name.replaceFirst("_", " ")
        }
        return this.name
    }
}