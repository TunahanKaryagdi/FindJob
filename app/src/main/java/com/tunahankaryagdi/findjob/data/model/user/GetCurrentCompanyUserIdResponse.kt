package com.tunahankaryagdi.findjob.data.model.user

import com.tunahankaryagdi.findjob.data.model.company.dtos.CompanyStaffDto

data class GetCurrentCompanyByUserIdResponse(
    val data: CompanyStaffDto?,
    val message: String,
    val success: Boolean
)