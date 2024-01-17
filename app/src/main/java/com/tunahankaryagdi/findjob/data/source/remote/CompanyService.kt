package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.company.PostCompanyRequest
import com.tunahankaryagdi.findjob.data.model.company.PostCompanyResponse
import retrofit2.http.POST


interface CompanyService{

    @POST("Companies")
    suspend fun postCompany(postCompanyRequest: PostCompanyRequest) : PostCompanyResponse
}