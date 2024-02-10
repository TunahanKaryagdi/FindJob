package com.tunahankaryagdi.findjob.data.model.user


data class CreateCompanyForUserRequest(
    val userId: String,
    val companyId: String,
    val title: String
)