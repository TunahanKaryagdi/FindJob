package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateUserResponse
import com.tunahankaryagdi.findjob.data.model.user.GetUserByIdResponse
import com.tunahankaryagdi.findjob.data.model.user.GoogleSignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SigninResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST("Users")
    suspend fun createUser(@Body user: CreateUserRequest) : CreateUserResponse

    @GET("Users/{id}")
    suspend fun getUserById(@Path("id") id: String) : GetUserByIdResponse

    @POST("Users/signin")
    suspend fun signIn(@Body signinRequest: SignInRequest) : SigninResponse

    @POST("Users/googlesignin")
    suspend fun signInWithGoogle(@Body googleSignInRequest: GoogleSignInRequest) : Unit



}