package com.example.retrofit_practice.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_practice.CovidService
import com.example.retrofit_practice.network.entity.history.HistoryByCountry
import com.example.retrofit_practice.util.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryPerCountryViewModel(val client: CovidService) : ViewModel(), RetrofitUpdatable {

    val confirmedData = MutableLiveData<Map<String, HistoryByCountry>>()
    val deathsData = MutableLiveData<Map<String, HistoryByCountry>>()
    val busy = MutableLiveData(false)

    override fun updateData(countryName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            busy.postValue(true)
            confirmedData.postValue(client.historyByCountry(Status.Confirmed.value, countryName))
            deathsData.postValue(client.historyByCountry(Status.Deaths.value, countryName))
            busy.postValue(false)
        }
    }
}