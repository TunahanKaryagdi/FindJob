package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.user.CreateCompanyForUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateCompanyForUserResponse
import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateUserResponse
import com.tunahankaryagdi.findjob.data.model.user.DeleteUserByIdResponse
import com.tunahankaryagdi.findjob.data.model.user.GetCompaniesByUserIdResponse
import com.tunahankaryagdi.findjob.data.model.user.GetCurrentCompanyByUserIdResponse
import com.tunahankaryagdi.findjob.data.model.user.GetUserByIdResponse
import com.tunahankaryagdi.findjob.data.model.user.GetUsersResponse
import com.tunahankaryagdi.findjob.data.model.user.GoogleSignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SigninResponse
import com.tunahankaryagdi.findjob.data.model.user.UpdateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.UpdateUserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import java.io.File

interface UserService {

    @POST("Users")
    suspend fun createUser(@Body user: CreateUserRequest) : CreateUserResponse

    @GET("Users")
    suspend fun getUsers() : GetUsersResponse

    @DELETE("Users/{id}")
    suspend fun deleteUserById(@Path("id") id: String) : DeleteUserByIdResponse

    @GET("Users/{id}")
    suspend fun getUserById(@Path("id") id: String) : GetUserByIdResponse

    @GET("Users/{id}/Companies")
    suspend fun getCompaniesByUserId(@Path("id") id: String) : GetCompaniesByUserIdResponse

    @GET("Users/{id}/CurrentCompany")
    suspend fun getCurrentCompanyByUserId(@Path("id") id: String) : GetCurrentCompanyByUserIdResponse

    @POST("Users/Companies")
    suspend fun createCompanyForUser(@Body createCompanyForUserRequest: CreateCompanyForUserRequest) : CreateCompanyForUserResponse

    @Multipart
    @PUT("Users")
    suspend fun updateUser(
        @Part("jsonBody") jsonBody: RequestBody,
        @Part file: MultipartBody.Part? = null
    ) : UpdateUserResponse

    @POST("Users/signin")
    suspend fun signIn(@Body signinRequest: SignInRequest) : SigninResponse

    @POST("Users/googlesignin")
    suspend fun signInWithGoogle(@Body googleSignInRequest: GoogleSignInRequest)

}