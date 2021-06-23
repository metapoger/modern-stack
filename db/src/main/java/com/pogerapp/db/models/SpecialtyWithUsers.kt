package com.pogerapp.db.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity
class DepartmentWithUsers(
    @Embedded val department: DepartmentModel,
    @Relation(
        parentColumn = "departmentId",
        entityColumn = "uid",
        associateBy = Junction(UserDepartmentCrossRef::class)
    )
    val users: List<UserModel>
)