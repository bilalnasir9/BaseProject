package com.example.baseproject

import androidx.lifecycle.MutableLiveData
import com.example.baseproject.base.BaseViewModel

class MainViewModel:BaseViewModel() {
    val dummyText = MutableLiveData<String>()

    fun loadData() {
        launchWithErrorHandling {
            // Simulate API call or DB fetch
//            kotlinx.coroutines.delay(1000)
            dummyText.postValue("Hello from ViewModel, button clicked!")
        }
    }
}