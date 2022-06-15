package com.homework.homeworkkuritsyn.data.sharedpreferences

import android.content.Context
import com.homework.homeworkkuritsyn.data.network.models.UserDataModel
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(private val context: Context) {
    companion object {
        private const val AUTHORIZED_SHARED_PREFERENCES = "AuthorizedSharedPreferences"
        private const val AUTHORIZED_SHARED_PREFERENCES_KEY = "Authorized_key"
        private const val TOKEN_SHARED_PREFERENCES = "AuthorizedSharedPreferences"
        private const val TOKEN_SHARED_PREFERENCES_KEY = "Authorized_key"
        private const val USER_DATA_SHARED_PREFERENCES = "UserDataPreferences"
        private const val USER_FIRST_NAME_SHARED_PREFERENCES_KEY = "UserFirstName_key"
        private const val USER_LAST_NAME_SHARED_PREFERENCES_KEY = "UserLastName_key"
        private const val USER_PHONE_SHARED_PREFERENCES_KEY = "UserPhone_key"
        private const val USER_LOGIN_SHARED_PREFERENCES_KEY = "UserLogin_key"
        private const val USER_PASSWORD_SHARED_PREFERENCES_KEY = "UserPassword_key"
    }

    fun putToken(token: String) {
        context.getSharedPreferences(
            TOKEN_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .edit()
            .putString(TOKEN_SHARED_PREFERENCES_KEY, token)
            .apply()
    }

    fun getToken(): String {
        return context.getSharedPreferences(
            TOKEN_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .getString(TOKEN_SHARED_PREFERENCES_KEY, "").orEmpty()
    }

    fun isAuthorized(): Boolean {
        return context.getSharedPreferences(
            AUTHORIZED_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .getBoolean(AUTHORIZED_SHARED_PREFERENCES_KEY, false)
    }

    fun setAuthorized() {
        context.getSharedPreferences(
            AUTHORIZED_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .edit()
            .putBoolean(AUTHORIZED_SHARED_PREFERENCES_KEY, true)
            .apply()
    }

    fun setUserData(userDataModel: UserDataModel) {
        context.getSharedPreferences(
            USER_DATA_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .edit()
            .putString(USER_FIRST_NAME_SHARED_PREFERENCES_KEY, userDataModel.firstName)
            .putString(USER_LAST_NAME_SHARED_PREFERENCES_KEY, userDataModel.lastName)
            .putString(USER_PHONE_SHARED_PREFERENCES_KEY, userDataModel.phone)
            .apply()
    }

    fun getUserData(): UserDataModel {
        val userDataSharedPreferences = context.getSharedPreferences(
            USER_DATA_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
        val firstName =
            userDataSharedPreferences.getString(USER_FIRST_NAME_SHARED_PREFERENCES_KEY, "")
                .orEmpty()
        val lastName =
            userDataSharedPreferences.getString(USER_LAST_NAME_SHARED_PREFERENCES_KEY, "")
                .orEmpty()
        val phone =
            userDataSharedPreferences.getString(USER_PHONE_SHARED_PREFERENCES_KEY, "")
                .orEmpty()
        val login =
            userDataSharedPreferences.getString(USER_LOGIN_SHARED_PREFERENCES_KEY, "")
                .orEmpty()
        val password =
            userDataSharedPreferences.getString(USER_PASSWORD_SHARED_PREFERENCES_KEY, "")
                .orEmpty()

        return UserDataModel(
            firstName = firstName,
            lastName = lastName,
            phone = phone,
            login = login,
            password = password
        )
    }

}