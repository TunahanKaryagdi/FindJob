package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateUserResponse
import com.tunahankaryagdi.findjob.data.model.user.SigninRequest
import com.tunahankaryagdi.findjob.data.model.user.SigninResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("Users")
    suspend fun createUser(@Body user: CreateUserRequest) : CreateUserResponse

    @POST("Users/signin")
    suspend fun signin(@Body signinRequest: SigninRequest) : SigninResponse


}