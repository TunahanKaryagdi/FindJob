package com.tunahankaryagdi.findjob.data.repository

import com.tunahankaryagdi.findjob.data.model.company.GetCompaniesResponse
import com.tunahankaryagdi.findjob.data.model.company.PostCompanyRequest
import com.tunahankaryagdi.findjob.data.model.company.PostCompanyResponse
import com.tunahankaryagdi.findjob.data.source.remote.CompanyService
import com.tunahankaryagdi.findjob.domain.repository.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(private val companyService: CompanyService) : CompanyRepository {

    override suspend fun postCompany(postCompanyRequest: PostCompanyRequest): PostCompanyResponse {
        return companyService.postCompany(postCompanyRequest)
    }

    override suspend fun getCompanies(): GetCompaniesResponse {
        return companyService.getCompanies()
    }
}