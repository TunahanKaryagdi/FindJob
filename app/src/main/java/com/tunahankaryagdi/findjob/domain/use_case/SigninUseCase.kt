package com.tunahankaryagdi.findjob.domain.use_case

import com.tunahankaryagdi.findjob.data.model.user.SigninRequest
import com.tunahankaryagdi.findjob.data.model.user.SigninResponse
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SigninUseCase @Inject constructor(private val userRepository: UserRepository){

    operator fun invoke(signinRequest: SigninRequest) : Flow<Resource<SigninResponse>> {
        return flow {
            try {
                val response = userRepository.signin(signinRequest)
                emit(Resource.Success(response))
            }
            catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: ""))
            }
        }
    }
}