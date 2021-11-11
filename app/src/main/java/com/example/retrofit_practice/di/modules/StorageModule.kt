package com.example.retrofit_practice.di.modules

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.retrofit_practice.util.PreferencesWorker
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    fun providePreferences(context: Context):SharedPreferences = context.getSharedPreferences("covid",Context.MODE_PRIVATE)

//    @Singleton
//    @Provides
//    fun provideWorker():PreferencesWorker = PreferencesWorker()
}