package com.pogerapp.db

import com.pogerapp.core.entity.user.User

interface UserDataStorage {
    suspend fun addUser(user: User)
    suspend fun addAllUsers(users: List<User>)
    suspend fun getUsers(): List<User>
    suspend fun clearUsers()
}