package com.tunahankaryagdi.findjob.domain.use_case.user

import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteUserByUserIdUseCase @Inject constructor(private val userRepository: UserRepository){
    operator fun invoke(id: String) : Flow<Resource<Boolean>> {

        return flow {

            try {
                val response = userRepository.deleteUserById(id)
                emit(Resource.Success(response.success))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}