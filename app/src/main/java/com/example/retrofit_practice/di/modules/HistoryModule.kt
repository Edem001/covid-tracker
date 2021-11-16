package com.example.retrofit_practice.di.modules

import com.example.retrofit_practice.CovidService
import com.example.retrofit_practice.vm.HistoryPerCountryViewModel
import dagger.Module
import dagger.Provides

@Module
class HistoryModule {
    @Provides
    fun provideHistoryViewModel(client: CovidService) = HistoryPerCountryViewModel(client)
}