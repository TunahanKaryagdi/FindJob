package com.tunahankaryagdi.findjob.domain.use_case

import com.tunahankaryagdi.findjob.data.model.user.dtos.toUserDetail
import com.tunahankaryagdi.findjob.domain.model.user.UserDetail
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository){
    operator fun invoke() : Flow<Resource<List<UserDetail>>> {

        return flow {

            try {
                val response = userRepository.getUsers()
                emit(Resource.Success(response.data.map { it.toUserDetail() }))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}