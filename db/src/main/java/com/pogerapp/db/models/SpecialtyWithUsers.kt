package com.pogerapp.db.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity
class SpecialtyWithUsers(
    @Embedded val specialty: SpecialtyModel,
    @Relation(
        parentColumn = "specialtyId",
        entityColumn = "uid",
        associateBy = Junction(UserSpecialtyCrossRef::class)
    )
    val users: List<UserModel>
)