package com.tunahankaryagdi.findjob.domain.use_case.user

import com.tunahankaryagdi.findjob.data.model.user.SignInRequest
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val userRepository: UserRepository){

    operator fun invoke(signInRequest: SignInRequest) : Flow<Resource<String>> {
        return flow {
            try {
                val response = userRepository.signIn(signInRequest)
                emit(Resource.Success(response.data))
            }
            catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: ""))
            }
        }
    }
}