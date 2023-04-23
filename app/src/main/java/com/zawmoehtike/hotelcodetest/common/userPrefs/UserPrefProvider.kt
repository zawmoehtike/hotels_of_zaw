package com.zawmoehtike.hotelcodetest.common.userPrefs

interface UserPrefProvider {
    fun setUserLoginStatus(status : Boolean)
    fun getUserLoginStatus() : Boolean
    fun clearAllPref()
}