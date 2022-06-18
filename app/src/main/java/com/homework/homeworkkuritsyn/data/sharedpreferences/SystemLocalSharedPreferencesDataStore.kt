package com.homework.homeworkkuritsyn.data.sharedpreferences

import timber.log.Timber
import javax.inject.Inject

class SystemLocalSharedPreferencesDataStore @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) {
    fun putToken(token: String) {
        sharedPreferencesManager.putToken(token)
    }

    fun getToken(): String {
        return sharedPreferencesManager.getToken()
    }

    fun isAuthorized(): Boolean {
        return sharedPreferencesManager.isAuthorized()
    }

    fun setAuthorizedIsTrue() {
        Timber.v("SystemLocalSharedPreferencesDataStore setAuthorizedIsTrue")
        sharedPreferencesManager.setAuthorized()
    }

    fun deleteUserData() {
        sharedPreferencesManager.deleteUserData()
    }
}