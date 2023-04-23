package com.zawmoehtike.hotelcodetest.common.userPrefs

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserPrefProviderImpl @Inject constructor(
    @ApplicationContext context: Context
) : UserPrefProvider {

    private val sharedPreference = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "USER_PREF"
        private const val KEY_LOGIN_STATUS = "login_status"
    }

    override fun setUserLoginStatus(status: Boolean) {
        sharedPreference.edit {
            putBoolean(KEY_LOGIN_STATUS, status)
        }
    }

    override fun getUserLoginStatus(): Boolean {
        return sharedPreference.getBoolean(KEY_LOGIN_STATUS, false)
    }

    override fun clearAllPref() {
        sharedPreference.edit {
            remove(KEY_LOGIN_STATUS).commit()
            remove(PREF_NAME).commit()
        }
    }
}