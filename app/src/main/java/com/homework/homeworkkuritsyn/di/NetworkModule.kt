package com.homework.homeworkkuritsyn.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.homework.homeworkkuritsyn.data.network.ShiftService
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File

@Module
class NetworkModule {
    private companion object {
        const val CACHE_DIR_NAME = "okhttp_cache"
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun provideCache(applicationContext: Context): Cache {
        val cacheSize = 50L * 1024L * 1024L
        return Cache(File(applicationContext.cacheDir, CACHE_DIR_NAME), cacheSize)
    }

    @Provides
    fun provideHttpClient(
        authorizedRepository: Lazy<AuthorizedRepository>,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(Interceptor { chain ->
                val original: Request = chain.request()
                val token = authorizedRepository.get().getToken()
                if (token.isNotEmpty()) {
                    val requestWithToken = chain.request().newBuilder()
                        .addHeader("Authorization", token).build()
                    chain.proceed(requestWithToken)
                } else {
                    chain.proceed(original)
                }
            })
            .cache(cache)
            .build()

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://shiftlab.cft.ru:7777/")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideRetrofitShiftService(retrofit: Retrofit): ShiftService =
        retrofit.create(ShiftService::class.java)
}