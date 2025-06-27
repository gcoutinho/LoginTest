package com.testproject.data.remote

import com.testproject.data.model.User
import kotlinx.coroutines.delay

//Simulate the retrofit call
class Api {

    suspend fun login(username: String, password: String): User? {
        // Simulate network delay
        delay(1000)
        // Simulate successful login
        val loginSuccess = true
        return if(loginSuccess)
            User(
            id = "1",
            username = username,
            email = "a@a.com"
        )
        else null
    }
}