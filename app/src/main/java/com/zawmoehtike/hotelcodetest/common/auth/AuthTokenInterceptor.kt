package com.onenex.yla.common.auth

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(private val authStore: AuthStoreProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = authStore.getAuthToken() ?: return chain.proceed(chain.request().newBuilder().apply {
            addHeader("X-Requested-With", "XMLHttpRequest")
            addHeader("Content-Type","application/json")
        }.build())
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Bearer ${token.value}")
        requestBuilder.addHeader("X-Requested-With", "XMLHttpRequest")
        requestBuilder.addHeader("Content-Type","application/json")
        requestBuilder.addHeader("Accept", "application/json")
        val newRequest = requestBuilder.build()
        return chain.proceed(newRequest)
    }
}
