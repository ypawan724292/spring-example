package com.pavan.imageProcessing.models

data class AuthenticationRequest(
    val username: String,
    val password: String
)

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String
)

data class UserRegistrationRequest(
    val username: String,
    val password: String,
    val roles: String = "User"
)
