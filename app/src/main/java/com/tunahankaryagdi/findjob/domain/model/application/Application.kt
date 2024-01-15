package com.tunahankaryagdi.findjob.domain.model.application

import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.model.user.User

data class Application(
    val createdDate: String,
    val id: String,
    val job: Job,
    val status: Boolean,
    val updatedDate: String,
    val user: User,
    val message: String
)
