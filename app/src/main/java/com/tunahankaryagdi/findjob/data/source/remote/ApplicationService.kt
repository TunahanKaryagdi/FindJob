package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.application.GetApplicationsByJobIdResponse
import com.tunahankaryagdi.findjob.data.model.application.GetApplicationsByUserIdResponse
import com.tunahankaryagdi.findjob.data.model.application.GetApplicationsResponse
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationRequest
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationResponse
import com.tunahankaryagdi.findjob.data.model.application.UpdateApplicationRequest
import com.tunahankaryagdi.findjob.data.model.application.UpdateApplicationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApplicationService {

    @GET("Applications")
    suspend fun getApplications() : GetApplicationsResponse

    @POST("Applications")
    suspend fun postApplication(@Body postApplicationRequest: PostApplicationRequest) : PostApplicationResponse

    @PUT("Applications")
    suspend fun updateApplication(@Body updateApplicationRequest: UpdateApplicationRequest) : UpdateApplicationResponse

    @GET("Applications/User/{userId}")
    suspend fun getApplicationsByUserId(@Path("userId") userId: String) : GetApplicationsByUserIdResponse

    @GET("Applications/Job/{jobId}")
    suspend fun getApplicationsByJobId(@Path("jobId") userId: String) : GetApplicationsByJobIdResponse

}