package com.pogerapp.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.pogerapp.core.entity.user.User

@Entity(primaryKeys = ["uid", "specialtyId"])
class UserSpecialtyCrossRef(
    val uid: Int,
    val specialtyId: Int
)