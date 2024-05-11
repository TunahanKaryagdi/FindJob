package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.preferrred_location.PostPreferredLocationRequest
import com.tunahankaryagdi.findjob.data.model.preferrred_location.PostPreferredLocationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PreferredLocationService{
    @POST("PreferredLocations")
    suspend fun postPreferredLocation(@Body postPreferredLocationRequest: PostPreferredLocationRequest) : PostPreferredLocationResponse

}