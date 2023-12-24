package com.tunahankaryagdi.findjob.domain.model.job


import com.tunahankaryagdi.findjob.domain.model.company.Company



data class Job(
    val id: String,
    val createdDate: String,
    val updatedDate: String,
    val location: String,
    val salary: Int,
    val title: String,
    val type: String,
    val company: Company,
)