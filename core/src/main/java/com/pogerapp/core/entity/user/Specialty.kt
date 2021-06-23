package com.pogerapp.core.entity.user

import com.google.gson.annotations.SerializedName

data class Department(
    @SerializedName("department_id")
    val departmentId: Int,

    @SerializedName("name")
    val name: String
)