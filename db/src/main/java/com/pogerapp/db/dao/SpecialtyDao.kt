package com.pogerapp.db.dao

import androidx.room.*
import com.pogerapp.db.models.SpecialtyModel

@Dao
interface SpecialtyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(specialty: SpecialtyModel)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(specialties: List<SpecialtyModel>?)

    @Query("SELECT * FROM SpecialtyModel")
    suspend fun getDepartments(): List<SpecialtyModel>?
}