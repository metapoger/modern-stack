package com.pogerapp.db.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.pogerapp.core.entity.user.User

@Entity
class UserWithDepartments(
    @Embedded val user: UserModel,
    @Relation(
        parentColumn = "uid",
        entityColumn = "departmentId",
        associateBy = Junction(UserDepartmentCrossRef::class)
    )
    val departments: List<DepartmentModel>
){
    fun toEntity() = User(
        user.firstName,
        user.lastName,
        user.birthday,
        user.avatar,
        departments.map { it.toEntity() }
    )
}