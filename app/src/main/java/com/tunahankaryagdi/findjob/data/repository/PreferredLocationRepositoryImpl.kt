package com.tunahankaryagdi.findjob.data.repository

import com.tunahankaryagdi.findjob.data.model.preferrred_location.PostPreferredLocationRequest
import com.tunahankaryagdi.findjob.data.model.preferrred_location.PostPreferredLocationResponse
import com.tunahankaryagdi.findjob.data.source.remote.PreferredLocationService
import com.tunahankaryagdi.findjob.domain.repository.PreferredLocationRepository
import javax.inject.Inject


class PreferredLocationRepositoryImpl @Inject constructor(private val preferredLocationService: PreferredLocationService) :  PreferredLocationRepository {
    override suspend fun postPreferredLocation(postPreferredLocationRequest: PostPreferredLocationRequest): PostPreferredLocationResponse {
        return preferredLocationService.postPreferredLocation(postPreferredLocationRequest)
    }


}