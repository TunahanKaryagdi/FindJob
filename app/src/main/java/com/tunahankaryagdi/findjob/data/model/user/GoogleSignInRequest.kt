package com.tunahankaryagdi.findjob.data.model.user



data class GoogleSignInRequest(
    val id: String,
    val idToken: String,
    val name: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val photoUrl: String,
    val provider: String
)