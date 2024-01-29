package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.job.DeleteJobByIdResponse
import com.tunahankaryagdi.findjob.data.model.job.GetJobResponse
import com.tunahankaryagdi.findjob.data.model.job.GetJobsByUserIdResponse
import com.tunahankaryagdi.findjob.data.model.job.GetJobsResponse
import com.tunahankaryagdi.findjob.data.model.job.PostJobRequest
import com.tunahankaryagdi.findjob.data.model.job.PostJobResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface JobService {

    @GET("Jobs")
    suspend fun getJobs(@Query("Page") page: Int) : GetJobsResponse

    @POST("Jobs")
    suspend fun postJob(@Body postJobRequest: PostJobRequest) : PostJobResponse

    @DELETE("Jobs/{id}")
    suspend fun deleteJobById(@Path("id") jobId: String) : DeleteJobByIdResponse

    @GET("Jobs/{jobId}")
    suspend fun getJobById(@Path("jobId") jobId: String) : GetJobResponse

    @GET("Jobs/User/{userId}")
    suspend fun getJobsByUserId(@Path("userId") userId: String) : GetJobsByUserIdResponse
}