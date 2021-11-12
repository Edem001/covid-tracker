package com.example.retrofit_practice.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_practice.CovidService
import com.example.retrofit_practice.network.entity.cases.CasesPerCountry
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CasesPerCountryViewModel @Inject constructor(val client: CovidService): ViewModel() {

    val info = MutableLiveData<Map<String, CasesPerCountry>>()

    fun updateData(country: String){
        runBlocking { info.postValue(client.casesByCountry(country)) }
    }
}