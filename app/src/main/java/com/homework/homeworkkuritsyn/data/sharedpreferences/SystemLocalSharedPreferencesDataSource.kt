package com.homework.homeworkkuritsyn.data.sharedpreferences

import javax.inject.Inject

class SystemLocalSharedPreferencesDataSource @Inject constructor(
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
        sharedPreferencesManager.setAuthorized()
    }

    fun deleteUserData() {
        sharedPreferencesManager.deleteUserData()
    }
}