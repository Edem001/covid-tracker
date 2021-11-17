package com.example.retrofit_practice.di.modules

import com.example.retrofit_practice.CovidService
import com.example.retrofit_practice.vm.HistoryPerCountryViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HistoryModule {
    @Singleton
    @Provides
    fun provideHistoryViewModel(client: CovidService) = HistoryPerCountryViewModel(client)
}