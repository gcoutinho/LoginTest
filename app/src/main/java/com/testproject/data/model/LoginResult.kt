package com.testproject.data.model

sealed class LoginResult {
    data class Success(val user: User) : LoginResult()
    data class Error(val message: String) : LoginResult()
}