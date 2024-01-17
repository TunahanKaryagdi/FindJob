package com.tunahankaryagdi.findjob.data.model.user.dtos

import com.tunahankaryagdi.findjob.domain.model.user.User

data class UserDto(
    val email: String,
    val id: String,
    val nameSurname: String,
    val image: String
)


fun UserDto.toUser(): User {
    return User(
        email, id, nameSurname, image
    )
}