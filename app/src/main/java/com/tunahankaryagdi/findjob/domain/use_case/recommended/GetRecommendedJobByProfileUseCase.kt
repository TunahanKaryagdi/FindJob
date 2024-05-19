package com.tunahankaryagdi.findjob.domain.use_case.recommended

import com.tunahankaryagdi.findjob.data.model.job.dtos.toJob
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.repository.RecommendedRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecommendedJobByProfileUseCase @Inject constructor(private val recommendedRepository: RecommendedRepository) {

    operator fun invoke(userId: String) : Flow<Resource<List<Job>>> {

        return flow {

            try {
                val response = recommendedRepository.getRecommendedJobByProfile(userId)
                emit(Resource.Success(response.data.map { it.toJob() }))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }

}