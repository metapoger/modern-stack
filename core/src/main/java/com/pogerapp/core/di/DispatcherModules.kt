package com.pogerapp.core.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DispatcherModules {
    @Provides
    @Singleton
    fun provideDispatcher() = Dispatchers.IO
}