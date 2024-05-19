package com.tunahankaryagdi.findjob.data.repository

import com.tunahankaryagdi.findjob.data.model.job.GetJobsResponse
import com.tunahankaryagdi.findjob.data.source.remote.RecommendedService
import com.tunahankaryagdi.findjob.domain.repository.RecommendedRepository
import javax.inject.Inject

class RecommendedRepositoryImpl @Inject constructor(private val recommendedService: RecommendedService): RecommendedRepository {
    override suspend fun getRecommendedJobByApplied(userId: String): GetJobsResponse {
        return  recommendedService.getRecommendedJobByApplied(userId)
    }

    override suspend fun getRecommendedJobByProfile(userId: String): GetJobsResponse {
        return  recommendedService.getRecommendedJobByProfile(userId)
    }
}