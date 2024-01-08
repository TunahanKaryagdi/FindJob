package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.application.GetApplicationsByJobIdResponse
import com.tunahankaryagdi.findjob.data.model.application.GetApplicationsByUserIdResponse
import com.tunahankaryagdi.findjob.data.model.application.GetApplicationsResponse
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationRequest
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationResponse
import retrofit2.http.Path

interface ApplicationRepository {

    suspend fun getApplications() : GetApplicationsResponse

    suspend fun postApplication( postApplicationRequest: PostApplicationRequest) : PostApplicationResponse

    suspend fun getApplicationsByUserId(userId: String) : GetApplicationsByUserIdResponse

    suspend fun getApplicationsByJobId( userId: String) : GetApplicationsByJobIdResponse


}