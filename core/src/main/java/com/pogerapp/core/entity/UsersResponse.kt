package com.pogerapp.core.entity

import com.google.gson.annotations.SerializedName

data class NetworkResponse<T>(
    @SerializedName("response")
    val data: T?
)