package com.pogerapp.db.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.pogerapp.core.entity.user.User

@Entity
class UserWithSpecialty(
    @Embedded val user: UserModel,
    @Relation(
        parentColumn = "uid",
        entityColumn = "specialtyId",
        entity = SpecialtyModel::class,
        associateBy = Junction(
            UserSpecialtyCrossRef::class,
            parentColumn = "uid",
            entityColumn = "specialtyId"
        )
    )
    val specialties: List<SpecialtyModel>
) {
    fun toEntity() = User(
        user.uid,
        user.firstName,
        user.lastName,
        user.birthday,
        user.avatar,
        specialties.map { it.toEntity() }
    )

    companion object {
        fun fromEntity(user: User) = UserWithSpecialty(
            UserModel.fromEntity(user),
            user.specialties?.map { SpecialtyModel.fromEntity(it) } ?: ArrayList()
        )
    }
}