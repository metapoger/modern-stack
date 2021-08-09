package com.pogerapp.core.entity.user

import com.google.gson.annotations.SerializedName

data class Specialty(
    @SerializedName("specialty_id")
    val specialtyId: Int,

    @SerializedName("name")
    val name: String
)