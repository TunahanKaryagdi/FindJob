package com.tunahankaryagdi.findjob.data.repository

import com.tunahankaryagdi.findjob.data.model.skill.PostSkillRequest
import com.tunahankaryagdi.findjob.data.model.skill.PostSkillResponse
import com.tunahankaryagdi.findjob.data.source.remote.SkillService
import com.tunahankaryagdi.findjob.domain.repository.SkillRepository
import javax.inject.Inject


class SkillRepositoryImpl @Inject constructor(private val skillService: SkillService) : SkillRepository {

    override suspend fun postSkill(postSkillRequest: PostSkillRequest): PostSkillResponse {
        return skillService.postSkill(postSkillRequest)
    }

}