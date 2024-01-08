package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.job.GetJobResponse
import com.tunahankaryagdi.findjob.data.model.job.GetJobsByUserIdResponse
import com.tunahankaryagdi.findjob.data.model.job.GetJobsResponse
import com.tunahankaryagdi.findjob.data.model.job.PostJobRequest
import com.tunahankaryagdi.findjob.data.model.job.PostJobResponse

interface JobRepository {

    suspend fun getJobs(page: Int) : GetJobsResponse
    suspend fun postJob(postJobRequest: PostJobRequest) : PostJobResponse
    suspend fun getJobById(jobId: String) : GetJobResponse
    suspend fun getJobsByUserId(userId: String) : GetJobsByUserIdResponse
}