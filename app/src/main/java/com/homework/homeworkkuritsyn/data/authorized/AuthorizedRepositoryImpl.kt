package com.homework.homeworkkuritsyn.data.authorized

import com.homework.homeworkkuritsyn.data.converters.*
import com.homework.homeworkkuritsyn.data.network.NetworkShiftDataStore
import com.homework.homeworkkuritsyn.data.sharedpreferences.SystemLocalSharedPreferencesDataStore
import com.homework.homeworkkuritsyn.domain.authorized.AuthResult
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import com.homework.homeworkkuritsyn.domain.entity.UserDataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class AuthorizedRepositoryImpl @Inject constructor(
    private val systemLocalSharedPreferencesDataStore: SystemLocalSharedPreferencesDataStore,
    private val networkShiftDataStore: NetworkShiftDataStore
) : AuthorizedRepository {
    override suspend fun isAuthorized(): Boolean = withContext(Dispatchers.IO) {
        systemLocalSharedPreferencesDataStore.isAuthorized()
    }

    override suspend fun setAuthorized() = withContext(Dispatchers.IO) {
        systemLocalSharedPreferencesDataStore.setAuthorized()
    }

    override fun getToken(): String =
        systemLocalSharedPreferencesDataStore.getToken()

    override suspend fun deleteUserData() {
        withContext(Dispatchers.IO) {
            systemLocalSharedPreferencesDataStore.deleteUserData()
        }
    }

    override suspend fun setToken(token: String) = withContext(Dispatchers.IO) {
        systemLocalSharedPreferencesDataStore.putToken(token)
    }

    override suspend fun signIn(authEntity: AuthEntity): AuthResult {
        val auth = AuthConverter(authEntity).asEntities()
        return withContext(Dispatchers.IO) {
            try {
                val token = networkShiftDataStore.signIn(auth = auth)
                setToken(token)
                AuthResult.Success
            } catch (httpException: HttpException) {
                when (httpException.code()) {
                    401 -> {
                        Timber.v(httpException.localizedMessage)
                        AuthResult.HttpError("Unauthorized")
                    }
                    403 -> {
                        Timber.v(httpException.localizedMessage)
                        AuthResult.HttpError("Forbidden")
                    }
                    404 -> {
                        Timber.v(httpException.localizedMessage)
                        AuthResult.HttpError("Not Found")
                    }
                    else -> {
                        Timber.v(httpException.localizedMessage)
                        AuthResult.HttpError("Another http error")
                    }
                }
            } catch (e: Exception) {
                Timber.v(e.stackTraceToString())
                AuthResult.OtherError("Another error")
            }
        }

    }

    override suspend fun signUp(authEntity: AuthEntity) {
        withContext(Dispatchers.IO) {
            try {
                val auth = AuthConverter(authEntity).asEntities()
                val userModel = networkShiftDataStore.signUp(auth = auth)
                Timber.v(userModel.toString())
            } catch (httpException: HttpException) {
                Timber.v(httpException.stackTraceToString())
            } catch (e: Exception) {
                Timber.v(e.stackTraceToString())
            }
        }
    }

}