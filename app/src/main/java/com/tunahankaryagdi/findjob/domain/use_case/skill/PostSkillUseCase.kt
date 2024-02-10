package com.tunahankaryagdi.findjob.domain.use_case.skill

import com.tunahankaryagdi.findjob.data.model.skill.PostSkillRequest
import com.tunahankaryagdi.findjob.domain.repository.SkillRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostSkillUseCase @Inject constructor(private val skillRepository: SkillRepository){

    operator fun invoke(postSkillRequest: PostSkillRequest) : Flow<Resource<Boolean>> {

        return flow {

            try {
                val response = skillRepository.postSkill(postSkillRequest)
                emit(Resource.Success(response.success))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}