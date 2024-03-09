package com.tunahankaryagdi.findjob.data.model.company.dtos

import com.tunahankaryagdi.findjob.data.model.user.dtos.UserDto
import com.tunahankaryagdi.findjob.data.model.user.dtos.toUser
import com.tunahankaryagdi.findjob.domain.model.company.CompanyStaff


data class CompanyStaffDto(
    val company: CompanyDto,
    val user: UserDto,
    val title: String
)

fun CompanyStaffDto.toCompanyStaff() : CompanyStaff{
    return CompanyStaff(
        company.toCompany(),user.toUser(),title
    )
}