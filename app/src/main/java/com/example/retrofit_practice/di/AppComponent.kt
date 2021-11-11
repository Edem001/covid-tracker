package com.example.retrofit_practice.di

import com.example.retrofit_practice.di.modules.NetworkModule
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {
}