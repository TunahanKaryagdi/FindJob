package com.tunahankaryagdi.findjob.domain.use_case.job

import com.tunahankaryagdi.findjob.data.model.job.dtos.toJob
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.repository.JobRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetJobsByUserIdUseCase @Inject constructor(private val jobRepository: JobRepository){
    operator fun invoke(userId: String) : Flow<Resource<List<Job>>> {

        return flow {

            try {
                val response = jobRepository.getJobsByUserId(userId)
                emit(Resource.Success(response.data.map { it.toJob() }))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}