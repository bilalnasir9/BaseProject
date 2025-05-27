package com.example.baseproject.retrofit.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SomeDataViewModel @Inject constructor(private val repository: SomeDataRepository) :
    BaseViewModel() {
    val _someData = MutableLiveData<List<SomeData>>()
    val someData: LiveData<List<SomeData>> get() = _someData

    fun getSomeData() = launchOnIO {
//        _someData.value = repository.getSomeData()


        val result = repository.getSomeData()
        _someData.postValue(result)
    }


}