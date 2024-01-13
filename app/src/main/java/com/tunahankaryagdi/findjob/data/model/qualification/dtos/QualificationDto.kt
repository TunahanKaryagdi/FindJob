package com.tunahankaryagdi.findjob.data.model.qualification.dtos

import com.tunahankaryagdi.findjob.domain.model.qualification.Qualification

data class QualificationDto(
    val createdDate: String,
    val id: String,
    val jobId: String,
    val name: String,
    val updatedDate: String,
    val experience: Int
)


fun QualificationDto.toQualification(): Qualification{
    return Qualification(
        createdDate, id, jobId, name, updatedDate, experience
    )
}