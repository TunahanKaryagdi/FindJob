package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.application.GetApplicationsResponse
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationRequest
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApplicationService {

    @GET("Applications")
    suspend fun getApplications() : GetApplicationsResponse

    @POST("Applications")
    suspend fun postApplication(@Body postApplicationRequest: PostApplicationRequest) : PostApplicationResponse

    @GET("Applications/{userId}")
    suspend fun getApplicationsByUserId(@Path("userId") userId: String) : GetApplicationsResponse

}