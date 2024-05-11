package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.skill.PostSkillRequest
import com.tunahankaryagdi.findjob.data.model.skill.PostSkillResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SkillService{
    @POST("Skills")
    suspend fun postSkill(@Body postSkillRequest: PostSkillRequest) : PostSkillResponse

}