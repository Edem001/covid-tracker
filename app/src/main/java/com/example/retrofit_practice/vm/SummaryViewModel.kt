package com.example.retrofit_practice.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_practice.network.CovidService
import com.example.retrofit_practice.util.PreferencesWorker
import kotlinx.coroutines.*
import javax.inject.Inject

class SummaryViewModel @Inject constructor(var worker: PreferencesWorker, var client: CovidService) : ViewModel() {

    val infected : MutableLiveData<Long> = MutableLiveData(0)
    val deaths : MutableLiveData<Long> = MutableLiveData(0)

    private final val UPDATE_DELAY: Long = 20 * 60 * 1000

    fun updateInfected(infectedCount: Long?){
        if (infectedCount == null) return
        infected.postValue(infectedCount)
        worker.writeInfected(infectedCount)
    }

    fun updateDeaths(deathsCount: Long?){
        if (deathsCount == null) return
        deaths.postValue(deathsCount)
        worker.writeDeaths(deathsCount)
    }

    val updaterJob = CoroutineScope(Dispatchers.IO).launch {
        repeat(Int.MAX_VALUE) {
            val summary = client.casesByCountry(country = "Global")["All"]

            updateInfected(summary?.confirmed)
            updateDeaths(summary?.deaths)

            delay(UPDATE_DELAY)
        }
    }

    fun updateData(){
        updaterJob.start()
        Log.d("Debug", "Update started")
    }

    fun interruptUpdating(){
        updaterJob.cancel()
        Log.d("Debug", "Update cancelled")
    }
}