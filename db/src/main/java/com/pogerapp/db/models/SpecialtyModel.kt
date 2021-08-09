package com.pogerapp.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pogerapp.core.entity.user.Specialty

@Entity
class SpecialtyModel(
    @PrimaryKey
    @ColumnInfo(name = "specialtyId")
    val specialtyId: Int,

    @ColumnInfo(name = "name")
    val name: String
) {

    companion object{
        fun fromEntity(specialty: Specialty) = SpecialtyModel(
            specialty.specialtyId,
            specialty.name
        )
    }

    fun toEntity() = Specialty(
        specialtyId,
        name
    )
}