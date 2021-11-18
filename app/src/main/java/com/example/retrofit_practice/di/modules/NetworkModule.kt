package com.example.retrofit_practice.di.modules

import android.content.Context
import com.example.retrofit_practice.network.CovidService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val cacheMaxSize = 1024 * 1024 * 10L

    @Singleton
    @Provides
    fun provideRetrofitClient(context: Context): CovidService {
        val cache = Cache(context.cacheDir, cacheMaxSize)

        val params = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .cache(cache)

        return Retrofit.Builder()
            .baseUrl("https://covid-api.mmediagroup.fr/v1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(params.build())
            .build()
            .create(CovidService::class.java)
    }
}