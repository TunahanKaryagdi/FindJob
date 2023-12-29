package com.tunahankaryagdi.findjob.data.model.job.dtos

import com.tunahankaryagdi.findjob.data.model.company.dtos.CompanyDto
import com.tunahankaryagdi.findjob.data.model.company.dtos.toCompany
import com.tunahankaryagdi.findjob.data.model.qualification.dtos.QualificationDto
import com.tunahankaryagdi.findjob.data.model.qualification.dtos.toQualification
import com.tunahankaryagdi.findjob.data.model.user.dtos.UserDto
import com.tunahankaryagdi.findjob.data.model.user.dtos.toUser
import com.tunahankaryagdi.findjob.domain.model.company.Company
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.model.job.JobDetail

data class JobDto(
    val company: CompanyDto,
    val createdDate: String,
    val id: String,
    val location: String,
    val qualifications: List<QualificationDto>,
    val salary: Int,
    val title: String,
    val type: String,
    val updatedDate: String,
    val user: UserDto
)

fun JobDto.toJob(): Job{
    return Job(
        id, createdDate, updatedDate, location, salary, title, type, company.toCompany()
    )
}

fun JobDto.toJobDetail() : JobDetail{
    return JobDetail(
        company.toCompany(),createdDate,id,location,qualifications.map { it.toQualification() },salary,title,type,updatedDate,user.toUser()
    )
}