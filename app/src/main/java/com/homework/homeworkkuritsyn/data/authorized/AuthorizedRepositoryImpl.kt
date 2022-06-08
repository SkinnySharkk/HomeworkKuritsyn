package com.homework.homeworkkuritsyn.data.authorized

import com.homework.homeworkkuritsyn.data.converters.AuthConverter
import com.homework.homeworkkuritsyn.data.converters.asModel
import com.homework.homeworkkuritsyn.data.network.NetworkShiftDataStore
import com.homework.homeworkkuritsyn.data.sharedpreferences.SystemLocalSharedPreferencesDataStore
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class AuthorizedRepositoryImpl @Inject constructor(
    private val systemLocalSharedPreferencesDataStore: SystemLocalSharedPreferencesDataStore,
    private val networkShiftDataStore: NetworkShiftDataStore
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

    override suspend fun signIn(authEntity: AuthEntity) {
        val auth = AuthConverter(authEntity).asModel()
        withContext(Dispatchers.IO) {
            try {
                networkShiftDataStore.signIn(auth = auth)
            } catch (e: Exception) {
                Timber.v(e.localizedMessage)
            }
        }
    }

    override suspend fun signUp(authEntity: AuthEntity) {
        val auth = AuthConverter(authEntity).asModel()
        withContext(Dispatchers.IO) {
            try {
               val userModel = networkShiftDataStore.signUp(auth = auth)
                Timber.v(userModel.toString())
            } catch (e: Exception) {
                Timber.v(e.localizedMessage)
            }
        }
    }

}