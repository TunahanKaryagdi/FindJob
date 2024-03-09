package com.tunahankaryagdi.findjob.domain.use_case.user

import com.tunahankaryagdi.findjob.data.model.company.dtos.toCompanyStaff
import com.tunahankaryagdi.findjob.domain.model.company.CompanyStaff
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentCompanyByUserIdUseCase @Inject constructor(private val userRepository: UserRepository){
    operator fun invoke(id: String) : Flow<Resource<CompanyStaff?>>{
        return flow {
            try {
                val response = userRepository.getCurrentCompanyByUserId(id)
                emit(Resource.Success(response.data?.toCompanyStaff()))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}