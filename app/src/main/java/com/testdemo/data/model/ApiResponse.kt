package com.testdemo.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
class ApiResponse(
    val statusCode: Int? = 0,
    val message: String? = "",
    val userData: UserData? = UserData()
) : Parcelable