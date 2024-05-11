package com.tunahankaryagdi.findjob.domain.use_case.preferred_location

import com.tunahankaryagdi.findjob.data.model.preferrred_location.PostPreferredLocationRequest
import com.tunahankaryagdi.findjob.domain.repository.PreferredLocationRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostPreferredLocationUseCase @Inject constructor(private val preferredLocationRepository: PreferredLocationRepository){

    operator fun invoke(postPreferredLocationRequest: PostPreferredLocationRequest) : Flow<Resource<Boolean>> {

        return flow {

            try {
                val response = preferredLocationRepository.postPreferredLocation(postPreferredLocationRequest)
                emit(Resource.Success(response.success))
            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}