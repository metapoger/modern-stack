package com.pogerapp.db.dao;

import androidx.room.*
import com.pogerapp.db.models.UserSpecialtyCrossRef

import com.pogerapp.db.models.UserWithSpecialty;

@Dao
interface UserWithSpecialtyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userWithSpecialty: UserSpecialtyCrossRef)

    @Transaction
    @Query("SELECT * FROM UserModel")
    suspend fun getUsersWithSpecialties(): List<UserWithSpecialty>
}
