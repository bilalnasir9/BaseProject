package com.example.baseproject.example

import com.example.baseproject.base.BaseRepository
import com.example.baseproject.utils.Resource

class UserRepository:BaseRepository() {
    suspend fun getUserData(): Resource<User> {

        return TODO("Provide the return value")
    }
}