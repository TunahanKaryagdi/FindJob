package com.tunahankaryagdi.findjob.data.model.company.dtos

import com.tunahankaryagdi.findjob.domain.model.company.CompanyStaff


data class CompanyStaffDto(
    val company: CompanyDto,
    val title: String
)

fun CompanyStaffDto.toCompanyStaff() : CompanyStaff{
    return CompanyStaff(
        company.toCompany(),title
    )
}