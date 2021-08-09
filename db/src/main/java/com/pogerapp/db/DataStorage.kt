package com.pogerapp.db

import androidx.room.RoomDatabase
import com.pogerapp.core.entity.user.Specialty
import com.pogerapp.core.entity.user.User
import com.pogerapp.db.dao.SpecialtyDao
import com.pogerapp.db.dao.UserDao
import com.pogerapp.db.dao.UserWithSpecialtyDao
import com.pogerapp.db.models.SpecialtyModel
import com.pogerapp.db.models.UserModel
import com.pogerapp.db.models.UserSpecialtyCrossRef
import com.pogerapp.db.models.UserWithSpecialty
import javax.inject.Inject

class DataStorage @Inject constructor(
    private val roomDatabase: Database
) : DataStorageRepository {

    override suspend fun addUser(user: User) {
        roomDatabase.userDao().insert(UserModel.fromEntity(user))
        roomDatabase.specialtyDao().addAll(user.specialties?.map { SpecialtyModel.fromEntity(it) })
    }

    override suspend fun addAllUsers(users: List<User>) {
        users.forEachIndexed{ index, user ->
            user.uid = index
            roomDatabase.userDao().insert(UserModel.fromEntity(user))
            user.specialties?.let { specs ->
                specs.forEach { spec ->
                    roomDatabase.specialtyDao().insert(SpecialtyModel.fromEntity(spec))
                    roomDatabase.userWithSpecialtyDao().insert(UserSpecialtyCrossRef(user.uid, spec.specialtyId))
                }
            }
        }
    }

    override suspend fun getUsers(): List<User>? {
        return roomDatabase.userDao().getAll().map {
            it.toEntity()
        }
    }

    override suspend fun getUsersByDepartment(depId: Int): List<User>? {
        return roomDatabase.userWithSpecialtyDao().getUsersWithSpecialties().map {
            it.toEntity()
        }.filter { uws->
            uws.specialties?.forEach {
            }
            uws.specialties?.firstOrNull { it.specialtyId == depId } != null
        }
    }

    override suspend fun addAllDepartments(specialties: List<Specialty>) {
        roomDatabase.specialtyDao().addAll(specialties.map { SpecialtyModel.fromEntity(it) })
    }

    override suspend fun getDepartments(): List<Specialty>? {
        return roomDatabase.specialtyDao().getDepartments()?.map {
            it.toEntity()
        }
    }

    override suspend fun clearAll() {
        roomDatabase.clearAllTables()
    }
}
