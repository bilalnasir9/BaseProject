package com.example.baseproject.retrofit.example

import com.example.baseproject.retrofit.ApiService
import javax.inject.Inject

class SomeDataRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getSomeData(): List<SomeData> = apiService.getSomeData()
}