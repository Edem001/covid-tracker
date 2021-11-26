package com.example.retrofit_practice.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_practice.network.CovidService
import com.example.retrofit_practice.network.entity.HistoryPerCountry
import com.example.retrofit_practice.util.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

class HistoryPerCountryViewModel @Inject constructor(val client: CovidService) : ViewModel() {

    val confirmedData = MutableLiveData<Map<String, HistoryPerCountry>>()
    val deathsData = MutableLiveData<Map<String, HistoryPerCountry>>()
    val busy = MutableLiveData(false)
    val timeout = MutableLiveData(false)

    fun updateData(countryName: String, status: Status) {
        CoroutineScope(Dispatchers.IO).launch {
            busy.postValue(true)
            if (status == Status.Confirmed)
                try {
                    confirmedData.postValue(client.historyByCountry(status.value, countryName))
                } catch (e: SocketTimeoutException) {
                    if (e.message == "timeout")
                        timeout.postValue(true)
                    else throw e
                }
            else
                try {
                    deathsData.postValue(client.historyByCountry(status.value, countryName))
                } catch (e: SocketTimeoutException) {
                    if (e.message == "timeout")
                        timeout.postValue(true)
                    else throw e
                }
            timeout.postValue(false)
            busy.postValue(false)
        }
    }
}