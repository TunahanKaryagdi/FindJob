package com.tunahankaryagdi.findjob.domain.use_case.user

import com.tunahankaryagdi.findjob.data.model.job.dtos.toJobDetail
import com.tunahankaryagdi.findjob.data.model.user.dtos.toUserDetail
import com.tunahankaryagdi.findjob.domain.model.job.JobDetail
import com.tunahankaryagdi.findjob.domain.model.user.UserDetail
import com.tunahankaryagdi.findjob.domain.repository.JobRepository
import com.tunahankaryagdi.findjob.domain.repository.UserRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val userRepository: UserRepository){
    operator fun invoke(id: String) : Flow<Resource<UserDetail>> {

        return flow {

            try {
                val response = userRepository.getUserById(id)
                emit(Resource.Success(response.data.toUserDetail()))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}