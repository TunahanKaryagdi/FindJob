package com.tunahankaryagdi.findjob.domain.use_case

import com.tunahankaryagdi.findjob.data.model.application.dtos.toApplication
import com.tunahankaryagdi.findjob.domain.model.application.Application
import com.tunahankaryagdi.findjob.domain.repository.ApplicationRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetApplicationsUseCase @Inject constructor(private val applicationRepository: ApplicationRepository){
    operator fun invoke() : Flow<Resource<List<Application>>> {

        return flow {

            try {
                val response = applicationRepository.getApplications()
                emit(Resource.Success(response.data.map { it.toApplication()}))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}