package com.pogerapp.network.di

import com.pogerapp.network.BuildConfig
import com.pogerapp.network.RestClient
import com.pogerapp.network.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttp
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
@InstallIn(ApplicationComponent::class)
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
    fun provideRestClient(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(RestClient::class.java)
}

@Module
abstract class NetworkBindings{
    @Binds
    abstract fun bindRestClientToUsersRepo(restClient: RestClient): UsersRepository
}