package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateUserResponse
import com.tunahankaryagdi.findjob.data.model.user.GetUserByIdResponse
import com.tunahankaryagdi.findjob.data.model.user.GoogleSignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SigninResponse

interface UserRepository {

    suspend fun createUser(createUserRequest: CreateUserRequest) : CreateUserResponse
    suspend fun getUserById(id: String) : GetUserByIdResponse
    suspend fun signIn(signInRequest: SignInRequest) : SigninResponse
    suspend fun signInWithGoogle(googleSignInRequest: GoogleSignInRequest)

}