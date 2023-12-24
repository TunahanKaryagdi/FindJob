package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.job.GetJobsResponse

interface JobRepository {

    suspend fun getJobs(page: Int) : GetJobsResponse
}