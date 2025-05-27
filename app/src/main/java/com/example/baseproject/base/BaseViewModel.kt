package com.example.baseproject.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel :ViewModel(){
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        isLoading.postValue(false)
        errorMessage.postValue(throwable.message ?: "Something went wrong")
    }
    fun launchWithErrorHandling(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(errorHandler) {
            isLoading.postValue(true)
            try {
                block()
            } catch (e: Exception) {
                errorMessage.postValue(e.localizedMessage)
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}