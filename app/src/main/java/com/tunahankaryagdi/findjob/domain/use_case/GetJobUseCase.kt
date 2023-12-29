package com.tunahankaryagdi.findjob.domain.use_case

import com.tunahankaryagdi.findjob.data.model.job.dtos.toJob
import com.tunahankaryagdi.findjob.data.model.job.dtos.toJobDetail
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.model.job.JobDetail
import com.tunahankaryagdi.findjob.domain.repository.JobRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetJobUseCase @Inject constructor(private val jobRepository: JobRepository){
    operator fun invoke(jobId: String) : Flow<Resource<JobDetail>> {

        return flow {

            try {
                val response = jobRepository.getJobById(jobId)
                emit(Resource.Success(response.data.toJobDetail()))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}