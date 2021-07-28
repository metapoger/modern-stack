package com.pogerapp.db.di

import android.content.Context
import androidx.room.Room
import com.pogerapp.db.DataStorage
import com.pogerapp.db.Database
import com.pogerapp.db.DataStorageRepository
import com.pogerapp.db.dao.DepartmentDao
import com.pogerapp.db.dao.UserDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModules {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database{
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "room-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: Database) = database.userDao()

    @Provides
    @Singleton
    fun provideDepartmentsDao(database: Database) = database.departmentDao()

    @Provides
    @Singleton
    fun provideDataStorage(userDao: UserDao, departmentDao: DepartmentDao) = DataStorage(userDao, departmentDao)
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataStorageBindings{
    @Binds
    @Singleton
    abstract fun bindToDataStorage(dataStorage: DataStorage): DataStorageRepository
}