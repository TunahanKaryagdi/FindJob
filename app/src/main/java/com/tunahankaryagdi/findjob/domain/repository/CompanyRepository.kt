package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.company.GetCompaniesResponse
import com.tunahankaryagdi.findjob.data.model.company.PostCompanyRequest
import com.tunahankaryagdi.findjob.data.model.company.PostCompanyResponse

interface CompanyRepository {
    suspend fun postCompany(postCompanyRequest: PostCompanyRequest) : PostCompanyResponse
    suspend fun getCompanies() : GetCompaniesResponse

}