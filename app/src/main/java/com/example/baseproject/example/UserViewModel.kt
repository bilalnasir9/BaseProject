package com.example.baseproject.example

import androidx.lifecycle.MutableLiveData
import com.example.baseproject.base.BaseViewModel
import com.example.baseproject.utils.Resource

class UserViewModel(val respository: UserRepository):BaseViewModel() {

    val userData = MutableLiveData<User>()
    fun loadUser() {
        launchOnMain {
            when (val result = respository.getUserData()) {
                is Resource.Success -> userData.postValue(result.data)
                is Resource.Error -> errorMessage.postValue(result.message)
                is Resource.Loading -> isLoading.postValue(true)
            }
        }
    }
}