package com.tunahankaryagdi.findjob.domain.use_case

import com.tunahankaryagdi.findjob.data.model.application.PostApplicationRequest
import com.tunahankaryagdi.findjob.data.model.job.PostJobRequest
import com.tunahankaryagdi.findjob.domain.repository.ApplicationRepository
import com.tunahankaryagdi.findjob.domain.repository.JobRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostApplicationUseCase @Inject constructor(private val applicationRepository: ApplicationRepository){

    operator fun invoke(postApplicationRequest: PostApplicationRequest) : Flow<Resource<Boolean>> {

        return flow {

            try {
                val response = applicationRepository.postApplication(postApplicationRequest)
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