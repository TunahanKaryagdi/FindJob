package com.tunahankaryagdi.findjob.data.model.user

data class CreateUserRequest(
    val nameSurname: String,
    val email :String,
    val password: String
)