package com.tunahankaryagdi.findjob.presentation.application

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.model.application.GetApplicationsByJobIdResponse
import com.tunahankaryagdi.findjob.domain.model.application.Application
import com.tunahankaryagdi.findjob.domain.use_case.GetApplicationsByJobIdUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.presentation.my_applications.MyApplicationsEffect
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ApplicationViewModel @Inject constructor(
    private val getApplicationsByJobIdUseCase: GetApplicationsByJobIdUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ApplicationUiState,ApplicationEffect,ApplicationEvent>(){

    private val jobId: String

    init {
        jobId = savedStateHandle.get<String>("jobId") ?: ""
        getApplicationsByJobId()
    }

    override fun setInitialState(): ApplicationUiState  = ApplicationUiState()

    override fun handleEvents(event: ApplicationEvent) {

    }

    private fun getApplicationsByJobId(){
        viewModelScope.launch {
            getApplicationsByJobIdUseCase.invoke(jobId).collect{resource->
                when(resource){
                    is Resource.Success->{
                        setState(getCurrentState().copy(applications = resource.data, isLoading = false))
                    }
                    is Resource.Error->{
                        setState(getCurrentState().copy(isLoading = false))
                    }
                }
            }
        }
    }
}

data class ApplicationUiState(
    val isLoading: Boolean = false,
    val applications: List<Application> = emptyList()
) : State

sealed interface ApplicationEffect : Effect {
    data class ShowMessage(val message: String) : ApplicationEffect
}

sealed interface ApplicationEvent : Event {

}

