package com.pogerapp.core.entity.user

import com.google.gson.annotations.SerializedName
import java.util.*

data class User(

    @SerializedName("uid")
    var uid: Int,

    @SerializedName("f_name")
    val firstName: String?,

    @SerializedName("l_name")
    val lastName: String?,

    @SerializedName("birthday")
    val birthday: String?,

    @SerializedName("avatr_url")
    val avatar: String?,

    @SerializedName("specialty")
    val specialties: List<Specialty>?
)