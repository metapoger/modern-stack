package com.pogerapp.network.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.pogerapp.network.BuildConfig
import com.pogerapp.network.DataRepository
import com.pogerapp.network.RestClient
import com.pogerapp.network.RestClientImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT_CONNECTION_SECONDS = 15L
private const val TIMEOUT_READ_SECONDS = 15L
private const val TIMEOUT_WRITE_SECONDS = 15L

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .callTimeout(TIMEOUT_CONNECTION_SECONDS, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .readTimeout(TIMEOUT_READ_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_WRITE_SECONDS, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRestClient(restClient: RestClient): RestClientImpl {
        return RestClientImpl(restClient)
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(RestClient::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkBindings{
    @Binds
    abstract fun bindRestClientToUsersRepo(restClient: RestClientImpl): DataRepository
}