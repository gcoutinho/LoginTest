package com.testproject.data.model

class User(
    val id: String,
    val username: String,
    val email: String,
    val fullName: String? = null,
    val profilePictureUrl: String? = null
)
