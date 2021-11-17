package com.example.retrofit_practice.di

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.retrofit_practice.MainActivity
import com.example.retrofit_practice.di.modules.CountryNamesModule
import com.example.retrofit_practice.di.modules.HistoryModule
import com.example.retrofit_practice.di.modules.NetworkModule
import com.example.retrofit_practice.di.modules.StorageModule
import com.example.retrofit_practice.fragments.*
import com.example.retrofit_practice.util.PreferencesWorker
import com.example.retrofit_practice.vm.MainActivityViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, StorageModule::class, Test::class, CountryNamesModule::class, HistoryModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: SummaryFragment)
    fun inject(fragment: CasesPerCountryFragment)
    fun inject(fragment: HistoryTabFragment)
    fun inject(fragment: VaccinatedPerCountryFragment)
}

@Module
class Test {

    @Provides
    fun test():MainActivityViewModel = MainActivityViewModel()
}