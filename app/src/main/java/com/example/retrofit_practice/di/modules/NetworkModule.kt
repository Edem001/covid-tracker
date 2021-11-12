package com.example.retrofit_practice.di.modules

import com.example.retrofit_practice.CovidService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(): CovidService =
        Retrofit.Builder()
            .baseUrl("https://covid-api.mmediagroup.fr/v1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(CovidService::class.java)
}