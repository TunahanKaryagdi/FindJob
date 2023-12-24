package com.tunahankaryagdi.findjob.data.model.company.dtos

import com.tunahankaryagdi.findjob.domain.model.company.Company

data class CompanyDto(
    val createdDate: String,
    val id: String,
    val name: String,
    val updatedDate: String
)

fun CompanyDto.toCompany(): Company{
    return Company(
        createdDate, id, name, updatedDate
    )
}