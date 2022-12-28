package ymsli.com.cpo.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Project Name : YSUP
 * @company  YMSLI
 * @author   VE00YM491
 * @date     27-11-2022
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

data class ServiceItemResponse(
    val Environment: String,
    val ErrorMessage: Any,
    val Result: List<ServiceDataResult>,
    val Status: Boolean,
    val StatusCode: Int,
    val SuccessMessage: Any,
    val Version: String
)

data class ServiceDataResult(
    val Description: String?,
    val Id: Int,
    val Name: String?,
    val UID: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Description)
        parcel.writeInt(Id)
        parcel.writeString(Name)
        parcel.writeString(UID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ServiceDataResult> {
        override fun createFromParcel(parcel: Parcel): ServiceDataResult {
            return ServiceDataResult(parcel)
        }

        override fun newArray(size: Int): Array<ServiceDataResult?> {
            return arrayOfNulls(size)
        }
    }
}