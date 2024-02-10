package com.tunahankaryagdi.findjob.domain.use_case.user

import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateUserResponse
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val userRepository: UserRepository){

    operator fun invoke(createUserRequest: CreateUserRequest) : Flow<Resource<CreateUserResponse>>{
        return flow {
            try {
                val response = userRepository.createUser(createUserRequest)
                emit(Resource.Success(response))
            }
            catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: ""))
            }
        }
    }
}