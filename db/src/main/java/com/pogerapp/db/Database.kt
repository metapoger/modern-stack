package com.pogerapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pogerapp.db.dao.DepartmentDao
import com.pogerapp.db.dao.UserDao
import com.pogerapp.db.models.DepartmentModel
import com.pogerapp.db.models.UserModel

@Database(entities = [UserModel::class, DepartmentModel::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun departmentDao(): DepartmentDao
}