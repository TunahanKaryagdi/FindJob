package com.tunahankaryagdi.findjob.data.model.application.dtos

import com.tunahankaryagdi.findjob.data.model.job.dtos.JobDto
import com.tunahankaryagdi.findjob.data.model.job.dtos.toJob
import com.tunahankaryagdi.findjob.data.model.user.dtos.UserDto
import com.tunahankaryagdi.findjob.data.model.user.dtos.toUser
import com.tunahankaryagdi.findjob.domain.model.application.Application

data class ApplicationDto(
    val createdDate: String,
    val id: String,
    val job: JobDto,
    val status: Boolean,
    val updatedDate: String,
    val user: UserDto,
    val message: String
)


fun ApplicationDto.toApplication() : Application{
    return Application(
        createdDate,id,job.toJob(),status,updatedDate,user.toUser(), message
    )
}