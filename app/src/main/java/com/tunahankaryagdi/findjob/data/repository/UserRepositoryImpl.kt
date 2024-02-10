package com.tunahankaryagdi.findjob.data.repository

import com.tunahankaryagdi.findjob.data.model.user.CreateCompanyForUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateCompanyForUserResponse
import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateUserResponse
import com.tunahankaryagdi.findjob.data.model.user.DeleteUserByIdResponse
import com.tunahankaryagdi.findjob.data.model.user.GetCompaniesByUserIdResponse
import com.tunahankaryagdi.findjob.data.model.user.GetUserByIdResponse
import com.tunahankaryagdi.findjob.data.model.user.GetUsersResponse
import com.tunahankaryagdi.findjob.data.model.user.GoogleSignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SigninResponse
import com.tunahankaryagdi.findjob.data.model.user.UpdateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.UpdateUserResponse
import com.tunahankaryagdi.findjob.data.source.remote.UserService
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) : UserRepository {

    override suspend fun createUser(createUserRequest: CreateUserRequest): CreateUserResponse {
        return userService.createUser(createUserRequest)
    }

    override suspend fun getUserById(id: String): GetUserByIdResponse {
        return userService.getUserById(id)
    }

    override suspend fun getUsers(): GetUsersResponse {
        return userService.getUsers()
    }

    override suspend fun getCompaniesByUserId(id: String): GetCompaniesByUserIdResponse {
        return userService.getCompaniesByUserId(id)
    }

    override suspend fun createCompanyForUser(createCompanyForUserRequest: CreateCompanyForUserRequest): CreateCompanyForUserResponse {
        return userService.createCompanyForUser(createCompanyForUserRequest)
    }

    override suspend fun deleteUserById(id: String): DeleteUserByIdResponse {
        return userService.deleteUserById(id)
    }

    override suspend fun updateUser(
        updateUserRequestBody: String,
        file: File?
    ): UpdateUserResponse {

        val jsonBodyRequest = updateUserRequestBody.toRequestBody("multipart/form-data".toMediaTypeOrNull())

        val fileRequestBody: RequestBody? = file?.let {
            it.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        }

        val filePart: MultipartBody.Part? = fileRequestBody?.let {
            MultipartBody.Part.createFormData("file", file.name, it)
        }

        return userService.updateUser(jsonBodyRequest,filePart)
    }


    override suspend fun signIn(signinRequest: SignInRequest): SigninResponse {
        return userService.signIn(signinRequest)
    }

    override suspend fun signInWithGoogle(googleSignInRequest: GoogleSignInRequest) {
        return userService.signInWithGoogle(googleSignInRequest)
    }
}