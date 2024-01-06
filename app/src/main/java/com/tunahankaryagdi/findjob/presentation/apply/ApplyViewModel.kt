package com.tunahankaryagdi.findjob.presentation.apply

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.model.application.PostApplicationRequest
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.use_case.PostApplicationUseCase
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
class ApplyViewModel @Inject constructor(
    private val tokenStore: TokenStore,
    private val savedStateHandle: SavedStateHandle,
    private val postApplicationUseCase: PostApplicationUseCase
): BaseViewModel<ApplyUiState,ApplyEffect,ApplyEvent>() {
    override fun setInitialState(): ApplyUiState = ApplyUiState()

    override fun handleEvents(event: ApplyEvent) {

        when(event){
            is ApplyEvent.OnEmailValueChange->{
                setState(getCurrentState().copy(email = event.value))
            }
            is ApplyEvent.OnNameValueChange->{
                setState(getCurrentState().copy(nameSurname = event.value))
            }
            is ApplyEvent.OnMessageValueChange->{
                setState(getCurrentState().copy(message = event.value))
            }
            is ApplyEvent.OnClickApply->{
                apply()
            }
        }

    }

    private fun apply(){
        viewModelScope.launch {
            tokenStore.getToken().collect{
                val jobId = savedStateHandle.get<String>("jobId") ?: ""
                val userId = JwtHelper.getUserId(it) ?: ""
                setState(getCurrentState().copy(isLoading = true))
                postApplicationUseCase.invoke(PostApplicationRequest(userId,jobId,getCurrentState().message)).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            if (resource.data){
                                setState(ApplyUiState())
                                setEffect(ApplyEffect.ShowMessage("successfully applied"))
                            }
                            else{
                                setEffect(ApplyEffect.ShowMessage("cannot applied"))
                            }
                            setState(getCurrentState().copy(isLoading = false))
                        }

                        is Resource.Error->{
                            setEffect(ApplyEffect.ShowMessage(resource.message))
                            setState(getCurrentState().copy(isLoading = false))
                        }
                    }

                }
            }
        }
    }
}


data class ApplyUiState(
    val isLoading: Boolean = false,
    val nameSurname: String = "",
    val email: String = "",
    val message: String = "",
) : State

sealed interface ApplyEffect : Effect{
    data class ShowMessage(val message: String) : ApplyEffect
}

sealed interface ApplyEvent : Event{
    data class OnNameValueChange(val value : String) : ApplyEvent
    data class OnEmailValueChange(val value : String) : ApplyEvent
    data class OnMessageValueChange(val value : String) : ApplyEvent
    object OnClickApply : ApplyEvent
}