package com.tunahankaryagdi.findjob.data.model.user

data class SigninResponse(
    val message: String,
    val success: Boolean,
    val token: Token
)

data class Token(
    val accessToken: String,
    val expirationTime: String
)