package com.pogerapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pogerapp.db.dao.SpecialtyDao
import com.pogerapp.db.dao.UserDao
import com.pogerapp.db.dao.UserWithSpecialtyDao
import com.pogerapp.db.models.SpecialtyModel
import com.pogerapp.db.models.UserModel
import com.pogerapp.db.models.UserSpecialtyCrossRef

@Database(entities = [UserModel::class, SpecialtyModel::class, UserSpecialtyCrossRef::class], version = 1, exportSchema = true)
abstract class Database: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun specialtyDao(): SpecialtyDao
    abstract fun userWithSpecialtyDao(): UserWithSpecialtyDao
}