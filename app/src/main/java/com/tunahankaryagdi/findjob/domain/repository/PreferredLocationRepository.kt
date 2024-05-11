package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.preferrred_location.PostPreferredLocationRequest
import com.tunahankaryagdi.findjob.data.model.preferrred_location.PostPreferredLocationResponse


interface PreferredLocationRepository {
    suspend fun postPreferredLocation(postPreferredLocationRequest: PostPreferredLocationRequest) : PostPreferredLocationResponse
}