package com.using.kotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Android(var logo: String, var codeName: String, var versionNumbers: String, var apiLevel: String, var releaseDate: String) : Parcelable