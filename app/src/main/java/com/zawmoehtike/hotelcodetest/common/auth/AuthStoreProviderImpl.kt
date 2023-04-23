package com.zawmoehtike.hotelcodetest.common.auth

import android.content.Context
import androidx.core.content.edit
import com.onenex.yla.common.auth.AuthStoreProvider
import com.onenex.yla.common.auth.AuthToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuthStoreProviderImpl @Inject constructor(
    @ApplicationContext context: Context
) : AuthStoreProvider {
    private val sharedPreference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        //SecureSharedPreference.get(context, PREF_NAME)

    companion object {
        private const val PREF_NAME = "AUTH_TOKEN"
        private const val KEY_AUTH_TOKEN = "auth_token"
    }

    override fun storeAuthToken(authToken: AuthToken) {
        sharedPreference.edit {
            putString(KEY_AUTH_TOKEN, authToken.value)
        }
    }

    override fun getAuthToken(): AuthToken? {
        val value = sharedPreference.getString(KEY_AUTH_TOKEN, null) ?: return null
        return AuthToken(value)
    }

    override fun clearAuthToken() {
        sharedPreference.edit {
            remove(KEY_AUTH_TOKEN)
        }
    }
}