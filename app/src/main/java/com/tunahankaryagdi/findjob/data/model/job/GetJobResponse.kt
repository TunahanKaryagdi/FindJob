package com.tunahankaryagdi.findjob.data.model.job

import com.tunahankaryagdi.findjob.data.model.job.dtos.JobDto

data class GetJobResponse(
    val data: JobDto,
    val message: String,
    val success: Boolean
)