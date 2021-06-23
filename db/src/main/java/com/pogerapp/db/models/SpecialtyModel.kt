package com.pogerapp.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pogerapp.core.entity.user.Department

@Entity
class DepartmentModel(
    @PrimaryKey
    @ColumnInfo(name = "departmentId")
    val departmentId: Int,

    @ColumnInfo(name = "name")
    val name: String
) {

    companion object{
        fun fromEntity(department: Department) = DepartmentModel(
            department.departmentId,
            department.name
        )
    }

    fun toEntity() = Department(
        departmentId,
        name
    )
}