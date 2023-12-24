package com.tunahankaryagdi.findjob.data.model.job

import com.tunahankaryagdi.findjob.data.model.job.dtos.JobDto

data class GetJobsResponse(
    val data: List<JobDto>,
    val message: String,
    val success: Boolean
)






