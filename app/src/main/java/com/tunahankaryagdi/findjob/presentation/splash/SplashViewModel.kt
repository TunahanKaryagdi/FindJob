package com.tunahankaryagdi.findjob.presentation.splash

import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.utils.JwtHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenStore: TokenStore
) : BaseViewModel<SplashUiState,SplashEffect,SplashEvent>() {
    override fun setInitialState(): SplashUiState = SplashUiState()

    init {
        checkIsLogged()
    }

    override fun handleEvents(event: SplashEvent) {

    }

    private fun checkIsLogged(){

        viewModelScope.launch {
            tokenStore.getToken().collect{token->
                if (token.isBlank()){
                    setEffect(SplashEffect.NavigateToLogin)
                }
                else{
                    if (JwtHelper.isTokenValid(token)){
                        setEffect(SplashEffect.NavigateToHome)
                    }
                    else{
                        setEffect(SplashEffect.ShowMessage("session expired"))
                        delay(1000)
                        setEffect(SplashEffect.NavigateToLogin)
                    }

                }
            }
        }

    }

}

data class SplashUiState(
    val isLoading: Boolean = false

) : State

sealed interface SplashEffect : Effect{
    object NavigateToHome : SplashEffect
    object NavigateToLogin : SplashEffect
    data class ShowMessage(val message: String) : SplashEffect
}

sealed interface SplashEvent : Event