package com.pogerapp.db.models

import androidx.room.Entity

@Entity(primaryKeys = ["uid", "department_id"])
class UserDepartmentCrossRef(
    val uid: Int,
    val departmentId: Int
)