package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.job.GetJobsResponse


interface RecommendedRepository {
    suspend fun getRecommendedJobByApplied(userId: String) : GetJobsResponse
    suspend fun getRecommendedJobByProfile(userId: String) : GetJobsResponse

}