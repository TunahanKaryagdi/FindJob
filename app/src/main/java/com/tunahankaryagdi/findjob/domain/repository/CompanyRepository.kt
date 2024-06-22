package com.tunahankaryagdi.findjob.domain.repository

import com.tunahankaryagdi.findjob.data.model.company.GetCompaniesResponse
import com.tunahankaryagdi.findjob.data.model.company.PostCompanyResponse
import java.io.File

interface CompanyRepository {
    suspend fun postCompany(postCompanyJsonBody: String, file: File? = null) : PostCompanyResponse
    suspend fun getCompanies() : GetCompaniesResponse

}
