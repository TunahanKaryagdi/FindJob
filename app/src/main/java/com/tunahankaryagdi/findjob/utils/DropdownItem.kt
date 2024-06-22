package com.tunahankaryagdi.findjob.utils




interface DropdownItem{
    val id: Int
    val name: String
}


data class Skill(
    override val id: Int,
    override val name: String
) : DropdownItem

data class ApplicationStatus(
    override val id: Int,
    override val name: String
) : DropdownItem

data class CompanyItem(
    override val id: Int,
    override val name: String,
    val companyId: String
) : DropdownItem

data class Location(
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
    Skill(7,"Agile scrum"),
    Skill(8,"MsSql"),
    Skill(9,"R"),
    Skill(10,"Matlab"),
    Skill(11,"C++"),
    Skill(12,"Network"),
    Skill(13,"Swift"),
    Skill(14,"HTML"),
    Skill(15,"CSS"),
    Skill(16,"Figma"),
    Skill(17,"Sketch"),
    Skill(18,"NoSQL"),
    Skill(19,"AWS"),
    Skill(20,"Google Cloud"),
    Skill(21,"Azure"),
    Skill(22,"Jira"),

)

val applicationStatuses = listOf(
    ApplicationStatus(1,"All"),
    ApplicationStatus(2,"Waiting"),
    ApplicationStatus(3,"Confirmed"),
)


val locations = listOf(
    Location(1,"İstanbul"),
    Location(2,"Konya"),
    Location(3,"Ankara"),
    Location(4,"İzmir"),
    Location(5,"Bursa"),
)