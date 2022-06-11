package com.homework.homeworkkuritsyn.data.network

import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import dagger.Lazy
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class LoginInterceptor @Inject constructor(
    private val lazyAuthorizedRepository: Lazy<AuthorizedRepository>
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = lazyAuthorizedRepository.get().getToken()
//        Timber.v(token)
        val request = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()
        return chain.proceed(request)
    }

}