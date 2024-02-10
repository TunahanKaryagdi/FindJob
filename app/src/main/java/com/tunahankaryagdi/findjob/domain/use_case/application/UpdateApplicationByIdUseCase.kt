package com.tunahankaryagdi.findjob.domain.use_case.application

import com.tunahankaryagdi.findjob.data.model.application.UpdateApplicationRequest
import com.tunahankaryagdi.findjob.data.model.application.dtos.toApplication
import com.tunahankaryagdi.findjob.domain.model.application.Application
import com.tunahankaryagdi.findjob.domain.repository.ApplicationRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateApplicationByIdUseCase @Inject constructor(private val applicationRepository: ApplicationRepository){
    operator fun invoke(updateApplicationRequest: UpdateApplicationRequest) : Flow<Resource<Boolean>> {

        return flow {

            try {
                val response = applicationRepository.updateApplication(updateApplicationRequest)
                emit(Resource.Success(response.success))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}