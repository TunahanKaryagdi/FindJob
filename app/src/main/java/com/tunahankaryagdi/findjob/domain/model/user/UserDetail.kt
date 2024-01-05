package com.tunahankaryagdi.findjob.domain.model.user



data class UserDetail(
    val email: String,
    val id: String,
    val nameSurname: String,
    val skills: List<Skill>
)
