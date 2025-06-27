package com.testproject.data.repository

import com.testproject.data.model.User
import com.testproject.data.remote.Api
import jakarta.inject.Inject

interface LoginRepository {
    suspend fun login(username: String, password: String): User?
}
class LoginRepositoryImpl @Inject constructor(
    private val api: Api
): LoginRepository {
    override suspend fun login(username: String, password: String): User? {
        return api.login(username, password)
    }
}