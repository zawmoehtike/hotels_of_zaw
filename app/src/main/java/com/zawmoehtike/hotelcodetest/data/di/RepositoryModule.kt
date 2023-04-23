package com.zawmoehtike.hotelcodetest.data.di

import com.zawmoehtike.hotelcodetest.data.repositories.HotelRepositoryImpl
import com.zawmoehtike.hotelcodetest.domain.repositories.HotelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class
RepositoryModule {

    @Binds
    abstract fun bindHotelRepository(hotelRepositoryImpl: HotelRepositoryImpl): HotelRepository
}