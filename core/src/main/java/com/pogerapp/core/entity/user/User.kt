package com.pogerapp.core.entity.user

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class User(
    @SerializedName("f_name")
    val firstName: String?,

    @SerializedName("l_name")
    val lastName: String?,

    @SerializedName("birthday")
    val birthday: Date?,

    @SerializedName("avatr_url")
    val avatar: String?,

    @SerializedName("department")
    val departments: List<Department>?
)