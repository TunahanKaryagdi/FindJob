package com.tunahankaryagdi.findjob.data.model.user.dtos

import com.tunahankaryagdi.findjob.domain.model.user.Skill

data class SkillDto(
    val createdDate: String,
    val id: String,
    val name: String,
    val updatedDate: String,
    val userId: String
)


fun SkillDto.toSkill() : Skill{
    return Skill(
        createdDate, id, name, updatedDate, userId
    )
}