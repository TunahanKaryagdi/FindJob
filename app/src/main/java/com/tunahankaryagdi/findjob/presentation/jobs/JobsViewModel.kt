package com.tunahankaryagdi.findjob.presentation.jobs

import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.use_case.GetJobsByUserIdUseCase
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
class JobsViewModel @Inject constructor(
    private val getJobsByUserIdUseCase: GetJobsByUserIdUseCase,
    private val tokenStore: TokenStore
) : BaseViewModel<JobsUiState,JobsEffect,JobsEvent>(){

    init{
        getJobsByUserId()
    }

    override fun setInitialState(): JobsUiState = JobsUiState()

    override fun handleEvents(event: JobsEvent) {

    }


    private fun getJobsByUserId(){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true))
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                getJobsByUserIdUseCase.invoke(userId).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(isLoading = false, jobs = resource.data))
                        }
                        is Resource.Error->{
                        }
                    }
                }
            }
        }
    }
}


data class JobsUiState(
    val isLoading: Boolean = false,
    val jobs: List<Job> = emptyList()
) : State

sealed interface JobsEffect : Effect{

}

sealed interface JobsEvent : Event{

}