package com.example.retrofit_practice.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_practice.CovidService
import com.example.retrofit_practice.network.entity.cases.CasesPerCountry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CasesPerCountryViewModel @Inject constructor(val client: CovidService): ViewModel(), RetrofitUpdatable {

    val data = MutableLiveData<Map<String, CasesPerCountry>>()
    val busy = MutableLiveData(false)

    override fun updateData(countryName: String){
        CoroutineScope(Dispatchers.IO).launch {
            busy.postValue(true)
            data.postValue(client.casesByCountry(countryName))
            busy.postValue(false)
        }
    }
}