package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.job.GetJobsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface JobService {

    @GET("Jobs")
    suspend fun getJobs(@Query("Page") page: Int) : GetJobsResponse
}