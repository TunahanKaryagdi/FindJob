package com.tunahankaryagdi.findjob.domain.use_case

import com.tunahankaryagdi.findjob.data.model.user.GoogleSignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SigninResponse
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(private val userRepository: UserRepository){

    operator fun invoke(googleSignInRequest: GoogleSignInRequest) : Flow<Resource<Boolean>> {
        return flow {
            try {
                val response = userRepository.signInWithGoogle(googleSignInRequest)
                emit(Resource.Success(true))
            }
            catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: ""))
            }
        }
    }
}