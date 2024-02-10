package com.tunahankaryagdi.findjob.domain.use_case.job

import com.tunahankaryagdi.findjob.data.model.job.PostJobRequest
import com.tunahankaryagdi.findjob.domain.repository.JobRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteJobByIdUseCase @Inject constructor(private val jobRepository: JobRepository){
    operator fun invoke(id: String) : Flow<Resource<Boolean>> {

        return flow {

            try {
                val response = jobRepository.deleteJobById(id)
                emit(Resource.Success(response.success))

            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}