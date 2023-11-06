package com.testdemo.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class UserData(
    val id: String? = null,
    val name: String? = null,
    val mobile: String? = null,
    val token: String? = null
): Parcelable {

}
