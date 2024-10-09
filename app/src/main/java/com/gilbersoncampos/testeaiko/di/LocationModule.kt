package com.gilbersoncampos.testeaiko.di

import android.content.Context
import com.gilbersoncampos.testeaiko.services.LocationService
import com.gilbersoncampos.testeaiko.services.LocationServiceImpl
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {
    @Singleton
    @Provides
    fun provideLocationClient(@ApplicationContext context: Context): LocationService =
        LocationServiceImpl(context, LocationServices.getFusedLocationProviderClient(context))
}