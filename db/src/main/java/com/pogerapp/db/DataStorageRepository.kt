package com.pogerapp.db

import com.pogerapp.core.entity.user.Specialty
import com.pogerapp.core.entity.user.User

interface DataStorageRepository {
    suspend fun addUser(user: User)
    suspend fun addAllUsers(users: List<User>)
    suspend fun getUsers(): List<User>?
    suspend fun getUsersByDepartment(depId: Int): List<User>?

    suspend fun addAllDepartments(specialties: List<Specialty>)
    suspend fun getDepartments(): List<Specialty>?

    suspend fun clearAll()
}