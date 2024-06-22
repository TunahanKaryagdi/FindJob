package com.tunahankaryagdi.findjob.data.source.remote

import com.tunahankaryagdi.findjob.data.model.company.GetCompaniesResponse
import com.tunahankaryagdi.findjob.data.model.company.PostCompanyResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface CompanyService{

    @Multipart
    @POST("Companies")
    suspend fun postCompany(
        @Part("jsonBody") jsonBody: RequestBody,
        @Part file: MultipartBody.Part? = null
    ) : PostCompanyResponse

    @GET("Companies")
    suspend fun getCompanies() : GetCompaniesResponse
}