package com.tunahankaryagdi.findjob.data.model.user


data class UpdateUserRequest(
    val id: String,
    val nameSurname: String,
    val email: String
)