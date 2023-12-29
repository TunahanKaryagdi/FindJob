package com.tunahankaryagdi.findjob.data.repository

import com.tunahankaryagdi.findjob.data.model.application.GetApplicationsResponse
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationRequest
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationResponse
import com.tunahankaryagdi.findjob.data.source.remote.ApplicationService
import com.tunahankaryagdi.findjob.domain.repository.ApplicationRepository
import javax.inject.Inject


class ApplicationRepositoryImpl @Inject constructor(private val applicationService: ApplicationService) : ApplicationRepository {
    override suspend fun getApplications(): GetApplicationsResponse {
        return applicationService.getApplications()
    }

    override suspend fun postApplication(postApplicationRequest: PostApplicationRequest): PostApplicationResponse {
        return applicationService.postApplication(postApplicationRequest)
    }

    override suspend fun getApplicationsByUserId(userId: String): GetApplicationsResponse {
        return applicationService.getApplicationsByUserId(userId)
    }
}