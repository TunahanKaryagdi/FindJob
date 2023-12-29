package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.application.GetApplicationsResponse
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationRequest
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationResponse

interface ApplicationRepository {

    suspend fun getApplications() : GetApplicationsResponse

    suspend fun postApplication( postApplicationRequest: PostApplicationRequest) : PostApplicationResponse

    suspend fun getApplicationsByUserId(userId: String) : GetApplicationsResponse

}