package com.example.retrofit_practice.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    val SearchQuery: MutableLiveData<String> = MutableLiveData()


}