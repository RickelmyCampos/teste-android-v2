package com.gilbersoncampos.testeaiko.di

import com.gilbersoncampos.testeaiko.data.repository.BusStopRepository
import com.gilbersoncampos.testeaiko.data.repository.BusStopRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class AppModule {
    @Binds
    abstract fun bindBusStopRepository(busStopRepositoryImpl: BusStopRepositoryImpl):BusStopRepository
}