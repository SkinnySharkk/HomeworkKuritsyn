package com.homework.homeworkkuritsyn.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
interface NetworkModule {
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl("shiftlab.cft.ru:7777/")
            .build()
}