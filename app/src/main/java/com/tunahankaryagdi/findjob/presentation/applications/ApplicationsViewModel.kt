package com.tunahankaryagdi.findjob.presentation.applications

import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.application.Application
import com.tunahankaryagdi.findjob.domain.use_case.GetApplicationsByUserIdUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.utils.JwtHelper
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationsViewModel @Inject constructor(
    private val getApplicationsByUserIdUseCase: GetApplicationsByUserIdUseCase,
    private val tokenStore: TokenStore
) : BaseViewModel<ApplicationsUiState,ApplicationsEffect,ApplicationsEvent>() {

    init {
        getApplicationsByUserId()
    }

    override fun setInitialState(): ApplicationsUiState = ApplicationsUiState()

    override fun handleEvents(event: ApplicationsEvent) {

    }

    private fun getApplicationsByUserId(){
        viewModelScope.launch {
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                setState(getCurrentState().copy(isLoading = true))
                getApplicationsByUserIdUseCase.invoke(userId).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(applications = resource.data, isLoading = false))
                        }
                        is Resource.Error->{
                            setState(getCurrentState().copy(isLoading = false))
                            setEffect(ApplicationsEffect.ShowMessage(resource.message))
                        }
                    }
                }
            }
        }
    }
}



data class ApplicationsUiState(
    val isLoading: Boolean = false,
    val applications: List<Application> = emptyList()
) : State

sealed interface ApplicationsEffect : Effect{
    data class ShowMessage(val message: String) : ApplicationsEffect
}

sealed interface ApplicationsEvent : Event{

}
