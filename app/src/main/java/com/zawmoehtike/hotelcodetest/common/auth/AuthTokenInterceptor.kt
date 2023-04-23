package com.zawmoehtike.hotelcodetest.common.auth

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(private val authStore: AuthStoreProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = authStore.getAuthToken()

        val requestBuilder = chain.request().newBuilder()

        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer ${it.value}")
        }

        //requestBuilder.addHeader("X-Requested-With", "XMLHttpRequest")
        requestBuilder.addHeader("Content-Language", "en_US")
        //requestBuilder.addHeader("Content-Type","application/json")
        requestBuilder.addHeader("Accept", "application/json")
        val newRequest = requestBuilder.build()
        return chain.proceed(newRequest)
    }
}
