package com.tunahankaryagdi.findjob.data.repository

import com.tunahankaryagdi.findjob.data.model.company.GetCompaniesResponse
import com.tunahankaryagdi.findjob.data.model.company.PostCompanyResponse
import com.tunahankaryagdi.findjob.data.source.remote.CompanyService
import com.tunahankaryagdi.findjob.domain.repository.CompanyRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(private val companyService: CompanyService) : CompanyRepository {


    override suspend fun postCompany(
        postCompanyJsonBody: String,
        file: File?
    ): PostCompanyResponse {
        val jsonBodyRequest = postCompanyJsonBody.toRequestBody("multipart/form-data".toMediaTypeOrNull())

        val fileRequestBody: RequestBody? = file?.let {
            it.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        }

        val filePart: MultipartBody.Part? = fileRequestBody?.let {
            MultipartBody.Part.createFormData("file", file.name, it)
        }

        return companyService.postCompany(jsonBodyRequest,filePart)
    }

    override suspend fun getCompanies(): GetCompaniesResponse {
        return companyService.getCompanies()
    }
}