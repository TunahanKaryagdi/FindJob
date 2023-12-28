package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.job.GetJobsResponse
import com.tunahankaryagdi.findjob.data.model.job.PostJobRequest
import com.tunahankaryagdi.findjob.data.model.job.PostJobResponse

interface JobRepository {

    suspend fun getJobs(page: Int) : GetJobsResponse

    suspend fun postJob(postJobRequest: PostJobRequest) : PostJobResponse
}