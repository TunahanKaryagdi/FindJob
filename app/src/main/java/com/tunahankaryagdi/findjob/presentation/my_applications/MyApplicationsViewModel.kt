package com.tunahankaryagdi.findjob.presentation.my_applications

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationsViewModel @Inject constructor(
    private val getApplicationsByUserIdUseCase: GetApplicationsByUserIdUseCase,
    private val tokenStore: TokenStore
) : BaseViewModel<MyApplicationsUiState,MyApplicationsEffect,MyApplicationsEvent>() {

    init {
        getApplicationsByUserId()
    }

    override fun setInitialState(): MyApplicationsUiState = MyApplicationsUiState()

    override fun handleEvents(event: MyApplicationsEvent) {

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
                            setEffect(MyApplicationsEffect.ShowMessage(resource.message))
                        }
                    }
                }
            }
        }
    }
}



data class MyApplicationsUiState(
    val isLoading: Boolean = false,
    val applications: List<Application> = emptyList()
) : State

sealed interface MyApplicationsEffect : Effect{
    data class ShowMessage(val message: String) : MyApplicationsEffect
}

sealed interface MyApplicationsEvent : Event{

}
