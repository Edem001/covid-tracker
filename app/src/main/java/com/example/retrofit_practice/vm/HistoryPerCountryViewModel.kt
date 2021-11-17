package com.example.retrofit_practice.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_practice.network.CovidService
import com.example.retrofit_practice.network.entity.HistoryPerCountry
import com.example.retrofit_practice.util.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryPerCountryViewModel @Inject constructor(val client: CovidService) : ViewModel() {

    val confirmedData = MutableLiveData<Map<String, HistoryPerCountry>>()
    val deathsData = MutableLiveData<Map<String, HistoryPerCountry>>()
    val busy = MutableLiveData(false)

    fun updateData(countryName: String, status: Status) {
        CoroutineScope(Dispatchers.IO).launch {
            busy.postValue(true)
            if (status == Status.Confirmed)
                confirmedData.postValue(client.historyByCountry(status.value, countryName))
            else
                deathsData.postValue(client.historyByCountry(status.value, countryName))
            busy.postValue(false)
        }
    }
}