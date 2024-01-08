package com.tunahankaryagdi.findjob.data.model.application

import com.tunahankaryagdi.findjob.data.model.application.dtos.ApplicationDto

data class GetApplicationsByUserIdResponse(
    val data: List<ApplicationDto>,
    val message: String,
    val success: Boolean
)