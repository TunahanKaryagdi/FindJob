package com.tunahankaryagdi.findjob.domain.model.job

import com.tunahankaryagdi.findjob.domain.model.company.Company
import com.tunahankaryagdi.findjob.domain.model.qualification.Qualification
import com.tunahankaryagdi.findjob.domain.model.user.User

data class JobDetail(
    val company: Company,
    val createdDate: String,
    val id: String,
    val location: String,
    val qualifications: List<Qualification>,
    val salary: Int,
    val title: String,
    val type: String,
    val updatedDate: String,
    val user: User,
)