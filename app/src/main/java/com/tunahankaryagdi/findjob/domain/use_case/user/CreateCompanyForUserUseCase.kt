package com.tunahankaryagdi.findjob.domain.use_case.user

import com.tunahankaryagdi.findjob.data.model.user.CreateCompanyForUserRequest
import com.tunahankaryagdi.findjob.data.model.user.dtos.toUserDetail
import com.tunahankaryagdi.findjob.domain.model.user.UserDetail
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateCompanyForUserUseCase @Inject constructor(private val userRepository: UserRepository){
    operator fun invoke(createCompanyForUserRequest: CreateCompanyForUserRequest) : Flow<Resource<Boolean>> {

        return flow {

            try {
                val response = userRepository.createCompanyForUser(createCompanyForUserRequest)
                emit(Resource.Success(response.success))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}