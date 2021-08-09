package com.pogerapp.db.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pogerapp.db.DataStorage
import com.pogerapp.db.Database
import com.pogerapp.db.DataStorageRepository
import com.pogerapp.db.dao.SpecialtyDao
import com.pogerapp.db.dao.UserDao
import com.pogerapp.db.dao.UserWithSpecialtyDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
    fun provideDataStorage(
        roomDatabase: Database
    ) = DataStorage(roomDatabase)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStorageBindings{
    @Binds
    @Singleton
    abstract fun bindToDataStorage(dataStorage: DataStorage): DataStorageRepository
}