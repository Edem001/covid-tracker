package com.example.retrofit_practice.di.modules

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.retrofit_practice.util.PreferencesWorker
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(/*includes = [StorageModule::class, StorageBindModule::class]*/)
class StorageModule {

    @Provides
    fun providePreferences(context: Context):SharedPreferences = context.getSharedPreferences("covid",Context.MODE_PRIVATE)

    @Provides
    fun provideWorker(sharedPreferences: SharedPreferences): PreferencesWorker = PreferencesWorker(sharedPreferences)
}

//@Module
//interface StorageBindModule {
//    @Singleton
//    @Binds
//    abstract fun provideWorker(sharedPreferences: SharedPreferences):PreferencesWorker
//}