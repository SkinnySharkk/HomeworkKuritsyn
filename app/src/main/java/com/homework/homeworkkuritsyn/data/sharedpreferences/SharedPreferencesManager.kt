package com.homework.homeworkkuritsyn.data.sharedpreferences

import android.content.Context
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(
    private val context: Context
) {
    companion object {
        private const val AUTHORIZED_SHARED_PREFERENCES = "AuthorizedSharedPreferences"
        private const val AUTHORIZED_SHARED_PREFERENCES_KEY = "Authorized_key"
        private const val TOKEN_SHARED_PREFERENCES = "TokenSharedPreferences"
        private const val TOKEN_SHARED_PREFERENCES_KEY = "Token_key"
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

    fun getToken(): String =
        context.getSharedPreferences(
            TOKEN_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .getString(TOKEN_SHARED_PREFERENCES_KEY, "").orEmpty()

    fun isAuthorized(): Boolean =
        context.getSharedPreferences(
            AUTHORIZED_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .getBoolean(AUTHORIZED_SHARED_PREFERENCES_KEY, false)


    fun setAuthorized() {
        context.getSharedPreferences(
            AUTHORIZED_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .edit()
            .putBoolean(AUTHORIZED_SHARED_PREFERENCES_KEY, true)
            .apply()
    }

    fun deleteUserData() {
        context.getSharedPreferences(
            AUTHORIZED_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .edit()
            .clear()
            .apply()
        context.getSharedPreferences(
            TOKEN_SHARED_PREFERENCES,
            Context.MODE_PRIVATE
        )
            .edit()
            .clear()
            .apply()
    }

}