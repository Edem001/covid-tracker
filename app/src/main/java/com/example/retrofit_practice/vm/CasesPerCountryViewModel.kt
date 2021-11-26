package com.example.retrofit_practice.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_practice.network.CovidService
import com.example.retrofit_practice.network.entity.CasesPerCountry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

class CasesPerCountryViewModel @Inject constructor(val client: CovidService): ViewModel(), RetrofitUpdatable {

    val data = MutableLiveData<Map<String, CasesPerCountry>>()
    val busy = MutableLiveData(false)
    val timeout = MutableLiveData(false)

    override fun updateData(countryName: String){
        CoroutineScope(Dispatchers.IO).launch {
            busy.postValue(true)
            try {
                data.postValue(client.casesByCountry(countryName))
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