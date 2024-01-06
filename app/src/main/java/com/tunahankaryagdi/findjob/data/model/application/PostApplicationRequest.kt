package com.tunahankaryagdi.findjob.data.model.application

data class PostApplicationRequest(
    val userId: String,
    val jobId: String,
    val message: String
)