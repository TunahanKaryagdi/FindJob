package com.tunahankaryagdi.findjob.domain.use_case

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
                if (response.success){
                    emit(Resource.Success(true))
                }
                else{
                    emit(Resource.Success(false))
                }

            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}