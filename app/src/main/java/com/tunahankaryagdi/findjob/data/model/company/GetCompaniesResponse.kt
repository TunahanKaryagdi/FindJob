package com.tunahankaryagdi.findjob.data.model.company

import com.tunahankaryagdi.findjob.data.model.company.dtos.CompanyDto

data class GetCompaniesResponse(
    val data: List<CompanyDto>,
    val message: String,
    val success: Boolean
)