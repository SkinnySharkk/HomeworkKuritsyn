package com.homework.homeworkkuritsyn.data.authorized

import com.homework.homeworkkuritsyn.data.sharedpreferences.SystemLocalSharedPreferencesDataStore
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import timber.log.Timber
import javax.inject.Inject

class AuthorizedRepositoryImpl @Inject constructor(
    private val systemLocalSharedPreferencesDataStore: SystemLocalSharedPreferencesDataStore
) : AuthorizedRepository {
    override fun isAuthorized(): Boolean {
        return systemLocalSharedPreferencesDataStore.isAuthorized()
    }

    override fun setAuthorized() {
        systemLocalSharedPreferencesDataStore.setAuthorized()
    }

    override fun getToken(): String {
        return systemLocalSharedPreferencesDataStore.getToken()
    }

    override fun setToken(token: String) {
        systemLocalSharedPreferencesDataStore.putToken(token)
    }

    override fun signIn(name: String, password: String) {
        Timber.v("signIn name = $name, password = $password")
    }

    override fun signUp(name: String, password: String) {
        Timber.v("signUp name = $name, password = $password")
    }

}