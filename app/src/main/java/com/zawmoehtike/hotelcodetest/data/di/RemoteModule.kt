package com.zawmoehtike.hotelcodetest.data.di

import com.onenex.yla.common.auth.AuthStoreProvider
import com.onenex.yla.common.auth.AuthTokenInterceptor
import com.squareup.moshi.Moshi
import com.zawmoehtike.hotelcodetest.BuildConfig
import com.zawmoehtike.hotelcodetest.data.remote.HotelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideAuthTokenInterceptor(
        authStoreProvider: AuthStoreProvider
    ) : AuthTokenInterceptor{
        return AuthTokenInterceptor(authStoreProvider)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authTokenInterceptor: AuthTokenInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(authTokenInterceptor)
            .addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.NONE
            }).build()
    }

    @Provides
    @Singleton
    fun provideMoshiBuilder(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi).asLenient()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideHotelApi(
        retrofit: Retrofit
    ): HotelApi {
        return retrofit.create(HotelApi::class.java)
    }
}