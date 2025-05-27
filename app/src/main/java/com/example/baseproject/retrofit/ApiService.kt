package com.example.baseproject.retrofit

import com.example.baseproject.retrofit.example.SomeData
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getSomeData(): List<SomeData>
}