package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateUserResponse
import com.tunahankaryagdi.findjob.data.model.user.DeleteUserByIdResponse
import com.tunahankaryagdi.findjob.data.model.user.GetUserByIdResponse
import com.tunahankaryagdi.findjob.data.model.user.GetUsersResponse
import com.tunahankaryagdi.findjob.data.model.user.GoogleSignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SigninResponse
import com.tunahankaryagdi.findjob.data.model.user.UpdateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.UpdateUserResponse
import retrofit2.http.Part
import java.io.File

interface UserRepository {

    suspend fun createUser(createUserRequest: CreateUserRequest) : CreateUserResponse
    suspend fun getUserById(id: String) : GetUserByIdResponse
    suspend fun getUsers() : GetUsersResponse
    suspend fun deleteUserById(id: String) : DeleteUserByIdResponse
    suspend fun updateUser(updateUserRequestBody: String,file: File? = null) : UpdateUserResponse
    suspend fun signIn(signInRequest: SignInRequest) : SigninResponse
    suspend fun signInWithGoogle(googleSignInRequest: GoogleSignInRequest)

}