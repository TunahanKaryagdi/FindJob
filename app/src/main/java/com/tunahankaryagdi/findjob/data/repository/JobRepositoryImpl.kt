package com.tunahankaryagdi.findjob.data.repository

import com.tunahankaryagdi.findjob.data.model.job.GetJobsResponse
import com.tunahankaryagdi.findjob.data.model.job.PostJobRequest
import com.tunahankaryagdi.findjob.data.model.job.PostJobResponse
import com.tunahankaryagdi.findjob.data.source.remote.JobService
import com.tunahankaryagdi.findjob.domain.repository.JobRepository
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(private val jobService: JobService) : JobRepository {


    override suspend fun getJobs(page: Int): GetJobsResponse {
        return jobService.getJobs(page)
    }

    override suspend fun postJob(postJobRequest: PostJobRequest): PostJobResponse {
        return jobService.postJob(postJobRequest)
    }
}