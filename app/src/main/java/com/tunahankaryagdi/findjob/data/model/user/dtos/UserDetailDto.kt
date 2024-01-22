package com.tunahankaryagdi.findjob.data.model.user.dtos

import com.tunahankaryagdi.findjob.data.model.skill.dtos.SkillDto
import com.tunahankaryagdi.findjob.data.model.skill.dtos.toSkill
import com.tunahankaryagdi.findjob.domain.model.user.UserDetail


data class UserDetailDto(
    val email: String,
    val id: String,
    val nameSurname: String,
    val skills: List<SkillDto>,
    val image: String?
)


fun UserDetailDto.toUserDetail() : UserDetail{
    return UserDetail(
        id = id,
        email = email,
        nameSurname = nameSurname,
        skills = skills.map { it.toSkill() },
        image = image
    )
}