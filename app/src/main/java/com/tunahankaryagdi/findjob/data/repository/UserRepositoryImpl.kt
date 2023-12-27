package com.tunahankaryagdi.findjob.data.repository

import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateUserResponse
import com.tunahankaryagdi.findjob.data.model.user.GoogleSignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SigninResponse
import com.tunahankaryagdi.findjob.data.source.remote.UserService
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) : UserRepository {

    override suspend fun createUser(createUserRequest: CreateUserRequest): CreateUserResponse {
        return userService.createUser(createUserRequest)
    }

    override suspend fun signIn(signinRequest: SignInRequest): SigninResponse {
        return userService.signIn(signinRequest)
    }

    override suspend fun signInWithGoogle(googleSignInRequest: GoogleSignInRequest) {
        return userService.signInWithGoogle(googleSignInRequest)
    }
}