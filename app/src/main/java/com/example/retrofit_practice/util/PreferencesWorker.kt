package com.example.retrofit_practice.util

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesWorker @Inject constructor(var storage: SharedPreferences) {

    fun getInfected() = storage.getLong("Infected", 0)
    fun getDeaths() = storage.getLong("Deaths", 0)

    fun writeInfected(infected: Long){ storage.edit().putLong("Infected", infected)}
    fun writeDeaths(deaths: Long){ storage.edit().putLong("Deaths", deaths)}
}