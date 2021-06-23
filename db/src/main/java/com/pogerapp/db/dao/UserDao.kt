package com.pogerapp.db.dao

import androidx.room.*
import com.pogerapp.core.entity.user.User
import com.pogerapp.db.models.UserModel
import com.pogerapp.db.models.UserWithDepartments

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    suspend fun insert(user: UserModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    suspend fun insertAll(user: List<User>)

    @Query("SELECT * FROM UserModel")
    suspend fun getAll(): List<UserWithDepartments>

    @Delete
    @Transaction
    suspend fun clear()
}