package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateUserResponse
import com.tunahankaryagdi.findjob.data.model.user.SigninRequest
import com.tunahankaryagdi.findjob.data.model.user.SigninResponse

interface UserRepository {

    suspend fun createUser(createUserRequest: CreateUserRequest) : CreateUserResponse
    suspend fun signin(signinRequest: SigninRequest) : SigninResponse

}