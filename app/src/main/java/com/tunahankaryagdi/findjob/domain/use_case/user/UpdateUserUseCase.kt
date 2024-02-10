package com.tunahankaryagdi.findjob.domain.use_case.user

import com.google.gson.Gson
import com.tunahankaryagdi.findjob.data.model.application.UpdateApplicationRequest
import com.tunahankaryagdi.findjob.data.model.user.UpdateUserRequest
import com.tunahankaryagdi.findjob.domain.repository.ApplicationRepository
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val userRepository: UserRepository){
    operator fun invoke(updateUserRequest: UpdateUserRequest,file: File?) : Flow<Resource<Boolean>> {

        return flow {
            try {
                val gson = Gson()
                val updateUserRequestBody = gson.toJson(updateUserRequest)
                val response = userRepository.updateUser(updateUserRequestBody, file)
                emit(Resource.Success(response.success))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}