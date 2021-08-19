package com.using.kotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Data {
    @SerializedName("data")
    @Expose
    var data: ArrayList<Android>? = null
}