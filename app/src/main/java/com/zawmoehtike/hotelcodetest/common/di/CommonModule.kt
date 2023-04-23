package com.zawmoehtike.hotelcodetest.common.di

import com.onenex.yla.common.auth.AuthStoreProvider
import com.zawmoehtike.hotelcodetest.common.auth.AuthStoreProviderImpl
import com.zawmoehtike.hotelcodetest.common.exception.ExceptionToStringMapper
import com.zawmoehtike.hotelcodetest.common.exception.ExceptionToStringMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModule {
    @Binds
    @Singleton
    abstract fun exceptionToStringMapper(exceptionToStringMapperImpl: ExceptionToStringMapperImpl): ExceptionToStringMapper

    @Binds
    abstract fun bindAuthStoreProvider(impl : AuthStoreProviderImpl) : AuthStoreProvider
}