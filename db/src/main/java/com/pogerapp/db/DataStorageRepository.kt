package com.pogerapp.db

import com.pogerapp.core.entity.user.Department
import com.pogerapp.core.entity.user.User

interface DataStorageRepository {
    suspend fun addUser(user: User)
    suspend fun addAllUsers(users: List<User>)
    suspend fun getUsers(): List<User>?
    suspend fun clearUsers()

    suspend fun addAllDepartments(departments: List<Department>)
    suspend fun getDepartments(): List<Department>?
    suspend fun clearDepartments()
}