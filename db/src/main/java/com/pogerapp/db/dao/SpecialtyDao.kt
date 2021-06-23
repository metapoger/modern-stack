package com.pogerapp.db.dao

import androidx.room.*
import com.pogerapp.db.models.DepartmentModel

@Dao
interface DepartmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(departments: List<DepartmentModel>?)

    @Query("SELECT * FROM DepartmentModel")
    suspend fun getDepartments(): List<DepartmentModel>?

    @Delete
    suspend fun clear()
}