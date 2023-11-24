package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateUserResponse

interface UserRepository {

    suspend fun createUser(createUserRequest: CreateUserRequest) : CreateUserResponse


}