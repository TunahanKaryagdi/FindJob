package com.tunahankaryagdi.findjob.domain.use_case

import com.tunahankaryagdi.findjob.data.model.job.PostJobRequest
import com.tunahankaryagdi.findjob.data.model.job.dtos.toJob
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.repository.JobRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostJobUseCase @Inject constructor(private val jobRepository: JobRepository){
    operator fun invoke(postJobRequest: PostJobRequest) : Flow<Resource<Boolean>> {

        return flow {

            try {
                val response = jobRepository.postJob(postJobRequest)
                emit(Resource.Success(response.success))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}