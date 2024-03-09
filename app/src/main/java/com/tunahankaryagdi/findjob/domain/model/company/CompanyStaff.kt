package com.tunahankaryagdi.findjob.domain.model.company

import com.tunahankaryagdi.findjob.domain.model.user.User


data class CompanyStaff(
    val company: Company,
    val user: User,
    val title: String
)