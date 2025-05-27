package com.example.baseproject

import androidx.lifecycle.MutableLiveData
import com.example.baseproject.base.BaseViewModel
import kotlinx.coroutines.delay

class HomeViewModel :BaseViewModel() {
    val text = MutableLiveData<String>()

    fun loadData() {
        launchWithErrorHandling {
            delay(300)
            text.postValue("Data loaded!")
        }
    }
}