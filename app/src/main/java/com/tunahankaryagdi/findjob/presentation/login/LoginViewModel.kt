package com.tunahankaryagdi.findjob.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel<LoginUiState,LoginEffect,LoginEvent>(){
    override fun setInitialState(): LoginUiState = LoginUiState()

    override fun handleEvents(event: LoginEvent) {
        TODO("Not yet implemented")
    }


}


data class LoginUiState(
    val isLoading : Boolean  = false
) : State

sealed interface LoginEffect : Effect{

}

sealed interface LoginEvent : Event{

}
