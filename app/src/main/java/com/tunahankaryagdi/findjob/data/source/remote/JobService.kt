package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.job.GetJobsResponse
import com.tunahankaryagdi.findjob.data.model.job.PostJobRequest
import com.tunahankaryagdi.findjob.data.model.job.PostJobResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface JobService {

    @GET("Jobs")
    suspend fun getJobs(@Query("Page") page: Int) : GetJobsResponse

    @POST("Jobs")
    suspend fun postJob(@Body postJobRequest: PostJobRequest) : PostJobResponse
}