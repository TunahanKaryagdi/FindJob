package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.skill.PostSkillRequest
import com.tunahankaryagdi.findjob.data.model.skill.PostSkillResponse


interface SkillRepository {

    suspend fun postSkill(postSkillRequest: PostSkillRequest) : PostSkillResponse

}