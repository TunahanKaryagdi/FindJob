package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateUserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST
    suspend fun createUser(@Body user: CreateUserRequest) : CreateUserResponse



}