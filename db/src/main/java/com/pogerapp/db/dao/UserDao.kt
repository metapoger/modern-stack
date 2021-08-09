package com.pogerapp.db.dao

import androidx.room.*
import com.pogerapp.db.models.UserModel
import com.pogerapp.db.models.UserWithSpecialty

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    suspend fun insert(user: UserModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    suspend fun insertAll(user: List<UserModel>)

    @Query("SELECT * FROM UserModel")
    suspend fun getAll(): List<UserWithSpecialty>

}