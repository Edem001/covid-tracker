package com.example.retrofit_practice.di.modules

import com.example.retrofit_practice.CovidService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitClient(): CovidService {
        val params = OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl("https://covid-api.mmediagroup.fr/v1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(params.build())
            .build()
            .create(CovidService::class.java)
    }
}