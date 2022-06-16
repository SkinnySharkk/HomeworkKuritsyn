package com.homework.homeworkkuritsyn.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.homework.homeworkkuritsyn.data.network.ShiftService
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    @Provides
    fun provideGson(): Gson =GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authorizedRepository: Lazy<AuthorizedRepository>
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
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