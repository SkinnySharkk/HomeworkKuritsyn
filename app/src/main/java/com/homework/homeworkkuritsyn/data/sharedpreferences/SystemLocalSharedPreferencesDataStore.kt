package com.homework.homeworkkuritsyn.data.sharedpreferences

import com.homework.homeworkkuritsyn.data.network.models.UserDataModel
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

    fun setAuthorized() {
        sharedPreferencesManager.setAuthorized()
    }

    fun setUserData(userDataModel: UserDataModel) {
        sharedPreferencesManager.setUserData(
            userDataModel
        )
    }
    fun getUserData(): UserDataModel {
        return sharedPreferencesManager.getUserData()
    }
}