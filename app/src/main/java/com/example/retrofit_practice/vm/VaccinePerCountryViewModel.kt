package com.example.retrofit_practice.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_practice.network.CovidService
import com.example.retrofit_practice.network.entity.VaccinePerCountry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class VaccinePerCountryViewModel @Inject constructor(val client: CovidService): ViewModel(), RetrofitUpdatable {

    val vaccinatedData = MutableLiveData<Map<String, VaccinePerCountry>>()
    val busy = MutableLiveData(false)

    override fun updateData(countryName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            busy.postValue(true)
            vaccinatedData.postValue(client.vaccineByCountry(countryName))
            busy.postValue(false)
        }
    }
}