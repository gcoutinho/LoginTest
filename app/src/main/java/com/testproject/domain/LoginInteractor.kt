package com.testproject.domain

import com.testproject.data.model.LoginResult
import com.testproject.data.model.User
import com.testproject.data.repository.LoginRepository
import jakarta.inject.Inject

interface LoginInteractor {
    suspend fun login(username: String, password: String): LoginResult
}

class LoginInteractorImpl @Inject constructor(
    val repository: LoginRepository
) : LoginInteractor {

    override suspend fun login(username: String, password: String): LoginResult {

        if (username.length < 8 || validPassword(password).not()) {
            throw IllegalArgumentException("Username and password cannot be empty")
        }

        val result = repository.login(username, password)
        return if (result != null) {
            // Simulate a successful login and return a dummy user
            LoginResult.Success(result)
        } else {
            LoginResult.Error("Login failed")
        }
    }

    private fun validPassword(password: String): Boolean {
        //password.hasNumber() && password.hasUpperCase() && password.hasLowerCase()
        return true
    }
}