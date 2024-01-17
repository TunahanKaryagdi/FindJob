package com.tunahankaryagdi.findjob.domain.use_case

import com.tunahankaryagdi.findjob.data.model.company.PostCompanyRequest
import com.tunahankaryagdi.findjob.domain.repository.CompanyRepository
import com.tunahankaryagdi.findjob.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostCompanyUseCase @Inject constructor(private val companyRepository: CompanyRepository){
    operator fun invoke(postCompanyRequest: PostCompanyRequest) : Flow<Resource<Boolean>> {

        return flow {

            try {
                val response = companyRepository.postCompany(postCompanyRequest)
                if (response.success){
                    emit(Resource.Success(true))
                }
                else{
                    emit(Resource.Success(false))
                }

            }
            catch (e: Exception){
                emit(Resource.Error(e.message ?: ""))
            }
        }
    }
}