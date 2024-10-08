package com.gilbersoncampos.testeaiko.di

import com.gilbersoncampos.testeaiko.data.remote.datasource.ApiDataSource
import com.gilbersoncampos.testeaiko.data.remote.datasource.ApiDataSourceImpl
import com.gilbersoncampos.testeaiko.data.remote.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val BASE_URL = ""
private const val READ_TIMEOUT_SECONDS = 60L
private const val CONNECT_TIMEOUT_SECONDS = 60L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit.Builder): ApiService =
        retrofit.baseUrl(BASE_URL).build().create(ApiService::class.java)

    @Provides
    fun providesApiHelper(helper: ApiDataSourceImpl): ApiDataSource = helper
}