package com.tunahankaryagdi.findjob.data.model.job

data class PostJobRequest(
    val companyId: String,
    val location: String,
    val qualifications: List<Qualification>,
    val salary: Int,
    val title: String,
    val type: String,
    val userId: String
)

data class Qualification(
    val name: String,
    val experience: Int
)