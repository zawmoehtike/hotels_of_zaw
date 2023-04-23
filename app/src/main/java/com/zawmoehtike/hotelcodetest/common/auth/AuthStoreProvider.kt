package com.zawmoehtike.hotelcodetest.common.auth

interface AuthStoreProvider {
    fun storeAuthToken(authToken: AuthToken)

    fun getAuthToken(): AuthToken?

    fun clearAuthToken()
}

@JvmInline
value class AuthToken(val value: String)