package com.example.retrofit_practice.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_practice.network.CovidService
import com.example.retrofit_practice.network.entity.VaccinePerCountry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

class VaccinePerCountryViewModel @Inject constructor(val client: CovidService): ViewModel(), RetrofitUpdatable {

    val vaccinatedData = MutableLiveData<Map<String, VaccinePerCountry>>()
    val busy = MutableLiveData(false)
    val timeout = MutableLiveData(false)

    override fun updateData(countryName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            busy.postValue(true)
            try {
                vaccinatedData.postValue(client.vaccineByCountry(countryName))
            } catch (e: SocketTimeoutException){
                if (e.message == "timeout")
                    timeout.postValue(true)
                else throw e
            }
            timeout.postValue(false)
            busy.postValue(false)
        }
    }
}