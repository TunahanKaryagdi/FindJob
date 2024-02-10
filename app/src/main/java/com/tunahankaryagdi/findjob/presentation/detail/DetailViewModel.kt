package com.tunahankaryagdi.findjob.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.job.JobDetail
import com.tunahankaryagdi.findjob.domain.use_case.application.GetApplicationsByUserIdUseCase
import com.tunahankaryagdi.findjob.domain.use_case.job.GetJobUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.utils.JwtHelper
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getJobUseCase: GetJobUseCase,
    private val getApplicationsByUserIdUseCase: GetApplicationsByUserIdUseCase,
    private val tokenStore: TokenStore,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailUiState,DetailEffect,DetailEvent>() {


    private val jobId: String
    init {
        jobId = savedStateHandle.get<String>("jobId") ?: ""
        getJob()
    }

    override fun setInitialState(): DetailUiState = DetailUiState()

    override fun handleEvents(event: DetailEvent) {
        when(event){
            is DetailEvent.OnClickApply->{
            }
        }

    }



    private fun getJob(){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true))
            getJobUseCase.invoke(jobId).collect{resource->
                when(resource){
                    is Resource.Success->{
                        isApplied()
                        setState(getCurrentState().copy(job = resource.data, isLoading = false))

                    }
                    is Resource.Error->{
                        setEffect(DetailEffect.ShowErrorMessage(resource.message))
                    }
                }
            }
        }
    }

    private fun isApplied(){
        viewModelScope.launch {
            tokenStore.getToken().collect{ token ->
                val userId = JwtHelper.getUserId(token) ?: ""
                getApplicationsByUserIdUseCase.invoke(userId).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            if (resource.data.any{it.job.id == jobId}){
                                setState(getCurrentState().copy(isApplied = true))
                            }
                        }
                        is Resource.Error->{

                        }
                    }
                }
            }
        }
    }
}


data class DetailUiState(
    val isLoading: Boolean = false,
    val job: JobDetail? = null,
    val isApplied: Boolean = false
) : State
sealed interface DetailEffect : Effect{
    data class ShowErrorMessage(val message: String) : DetailEffect
}

sealed interface DetailEvent : Event{
    object OnClickApply : DetailEvent
}