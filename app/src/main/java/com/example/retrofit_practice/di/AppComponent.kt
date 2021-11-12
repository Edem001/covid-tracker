package com.example.retrofit_practice.di

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.retrofit_practice.MainActivity
import com.example.retrofit_practice.di.modules.NetworkModule
import com.example.retrofit_practice.di.modules.StorageModule
import com.example.retrofit_practice.fragments.SummaryFragment
import com.example.retrofit_practice.util.PreferencesWorker
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, StorageModule::class, Test::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: SummaryFragment)

    var content: String
}

@Module
class Test {

    @Provides
    fun test(): String = "test"
}