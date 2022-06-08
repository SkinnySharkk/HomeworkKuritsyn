package com.homework.homeworkkuritsyn.di

import com.homework.homeworkkuritsyn.data.network.ShiftService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
interface NetworkModule {
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("shiftlab.cft.ru:7777/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    fun provideRetrofitShiftService(retrofit: Retrofit): ShiftService =
        retrofit.create(ShiftService::class.java)
}