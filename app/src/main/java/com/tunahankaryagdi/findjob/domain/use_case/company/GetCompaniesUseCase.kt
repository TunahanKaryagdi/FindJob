package com.tunahankaryagdi.findjob.domain.use_case.company

import com.tunahankaryagdi.findjob.data.model.company.PostCompanyRequest
import com.tunahankaryagdi.findjob.data.model.company.dtos.toCompany
import com.tunahankaryagdi.findjob.domain.model.company.Company
import com.tunahankaryagdi.findjob.domain.repository.CompanyRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCompaniesUseCase @Inject constructor(private val companyRepository: CompanyRepository){
    operator fun invoke() : Flow<Resource<List<Company>>> {

        return flow {

            try {
                val response = companyRepository.getCompanies()
                emit(Resource.Success(response.data.map { it.toCompany() }))

            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}