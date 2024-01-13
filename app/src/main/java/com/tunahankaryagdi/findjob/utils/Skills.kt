package com.tunahankaryagdi.findjob.utils




interface DropdownItem{
    val id: Int
    val name: String
}


data class Skill(
    override val id: Int,
    override val name: String
) : DropdownItem




val skills = listOf(
    Skill(1,"Java"),
    Skill(2,"Python"),
    Skill(3,"C#"),
    Skill(4,"Kotlin"),
    Skill(5,"JavaScript"),
    Skill(6,"C"),
)