package com.tunahankaryagdi.findjob.presentation.signup

import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.model.user.CreateUserRequest
import com.tunahankaryagdi.findjob.domain.use_case.CreateUserUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
    ) : BaseViewModel<SignupUiState,SignupEffect,SignupEvent>() {


    override fun setInitialState(): SignupUiState = SignupUiState()

    override fun handleEvents(event: SignupEvent) {
        when(event){
            is SignupEvent.OnClickSignup->{
                createUser()
            }
            is SignupEvent.OnNameValueChange->{
                setState(getCurrentState().copy(name = event.value))
            }
            is SignupEvent.OnSurnameValueChange->{
                setState(getCurrentState().copy(surname = event.value))
            }
            is SignupEvent.OnEmailValueChange->{
                setState(getCurrentState().copy(email = event.value))
            }
            is SignupEvent.OnPasswordValueChange->{
                setState(getCurrentState().copy(password = event.value))
            }
        }
    }

    private fun createUser(){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true))
            createUserUseCase.invoke(CreateUserRequest("","","")).collect{ resource->
                when(resource){
                    is Resource.Success ->{
                        setState(getCurrentState().copy(isLoading = false))
                    }
                    is Resource.Error ->{
                        setState(getCurrentState().copy(isLoading = false))
                        setEffect(SignupEffect.ShowErrorMessage(resource.message))
                    }
                }
            }
        }
    }


}


data class SignupUiState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false
) : State

sealed interface SignupEffect : Effect{
    data class ShowErrorMessage(val message : String) : SignupEffect
}

sealed interface SignupEvent : Event{
    object OnClickSignup : SignupEvent
    data class OnNameValueChange(val value : String) : SignupEvent
    data class OnSurnameValueChange(val value : String) : SignupEvent
    data class OnEmailValueChange(val value : String) : SignupEvent
    data class OnPasswordValueChange(val value : String) : SignupEvent

}