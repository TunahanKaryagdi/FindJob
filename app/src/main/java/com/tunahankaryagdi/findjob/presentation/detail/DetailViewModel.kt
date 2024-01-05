package com.tunahankaryagdi.findjob.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationRequest
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.job.JobDetail
import com.tunahankaryagdi.findjob.domain.use_case.GetJobUseCase
import com.tunahankaryagdi.findjob.domain.use_case.PostApplicationUseCase
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
    private val postApplicationUseCase: PostApplicationUseCase,
    private val tokenStore: TokenStore,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailUiState,DetailEffect,DetailEvent>() {

    init {
        savedStateHandle.get<String>("jobId")?.let {
            getJob(it)
        }

    }

    override fun setInitialState(): DetailUiState = DetailUiState()

    override fun handleEvents(event: DetailEvent) {
        when(event){
            is DetailEvent.OnClickApply->{
                apply()
            }
        }

    }

    private fun apply(){

        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true))
            tokenStore.getToken().collect{token->
                val userId = JwtHelper.getUserId(token) ?: ""
                val jobId = getCurrentState().job?.id ?: ""
                postApplicationUseCase.invoke(PostApplicationRequest(userId,jobId)).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(isLoading = false, isApplied = true))
                        }
                        is Resource.Error->{
                            setEffect(DetailEffect.ShowErrorMessage(resource.message))
                        }
                    }
                }
            }
        }
    }

    private fun getJob(jobId: String){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true))
            getJobUseCase.invoke(jobId).collect{resource->
                when(resource){
                    is Resource.Success->{
                        setState(getCurrentState().copy(job = resource.data, isLoading = false))
                    }
                    is Resource.Error->{
                        setEffect(DetailEffect.ShowErrorMessage(resource.message))
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