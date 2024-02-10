package com.tunahankaryagdi.findjob.data.model.user

import com.tunahankaryagdi.findjob.data.model.company.dtos.CompanyStaffDto

data class GetCompaniesByUserIdResponse(
    val data: List<CompanyStaffDto>,
    val message: String,
    val success: Boolean
)
