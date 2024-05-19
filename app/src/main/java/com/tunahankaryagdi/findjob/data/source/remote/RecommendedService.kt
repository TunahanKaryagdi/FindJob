package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.job.GetJobsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RecommendedService {
    @GET("Recommended/Applied/{userId}")
    suspend fun getRecommendedJobByApplied(@Path("userId") userId: String) : GetJobsResponse

    @GET("Recommended/Profile/{userId}")
    suspend fun getRecommendedJobByProfile(@Path("userId") userId: String) : GetJobsResponse
}