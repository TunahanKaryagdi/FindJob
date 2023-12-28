package com.tunahankaryagdi.findjob.presentation.login


import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.tunahankaryagdi.findjob.data.model.user.GoogleSignInRequest
import com.tunahankaryagdi.findjob.data.model.user.SignInRequest
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.use_case.SignInUseCase
import com.tunahankaryagdi.findjob.domain.use_case.SignInWithGoogleUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val tokenStore: TokenStore
) : BaseViewModel<LoginUiState,LoginEffect,LoginEvent>(){
    override fun setInitialState(): LoginUiState = LoginUiState()

    override fun handleEvents(event: LoginEvent) {
        when(event){
            is LoginEvent.OnEmailValueChange->{
                setState(getCurrentState().copy(email = event.value))
            }
            is LoginEvent.OnPasswordValueChange->{
                setState(getCurrentState().copy(password = event.value))
            }
            is LoginEvent.OnClickGoogleSignIn->{
                handleGoogleSignInResult(event.result)
            }
            is LoginEvent.OnClickLogin->{
                signIn()
            }
        }
    }

    private fun signIn(){
        val uiState = getCurrentState()
        if (uiState.isValid()){
            viewModelScope.launch {
                setState(getCurrentState().copy(isLoading = true))
                signInUseCase.invoke(SignInRequest(uiState.email.trim(),uiState.password.trim())).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            println("")
                            tokenStore.saveToken(resource.data)
                            setState(getCurrentState().copy(isLoading = false))
                            setEffect(LoginEffect.NavigateToHome)
                        }
                        is Resource.Error->{
                            setState(getCurrentState().copy(isLoading = false))
                            setEffect(LoginEffect.ShowErrorMessage(resource.message))
                        }
                    }
                }
            }
        }
    }



    private fun handleGoogleSignInResult(result: ActivityResult?) {

        if (result?.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let {
                    val id = account.id
                    val idToken = it.idToken
                    val fullName = it.displayName
                    val surname = it.familyName
                    val name = it.givenName
                    val email = it.email
                    val photoUrl = it.photoUrl
                    val provider = "GOOGLE"

                    signInWithGoogle(
                        GoogleSignInRequest(
                            id = id,
                            idToken = idToken,
                            name = fullName,
                            firstName = name,
                            lastName = surname,
                            email = email,
                            photoUrl = photoUrl.toString(),
                            provider = provider
                        )
                    )
                }

 }
            catch (e: Exception){
                setEffect(LoginEffect.ShowErrorMessage(e.message ?: ""))
            }
        } else {
            setEffect(LoginEffect.ShowErrorMessage("Cannot sign in with google"))
        }
    }


    private fun signInWithGoogle(googleSignInRequest: GoogleSignInRequest){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true))
            signInWithGoogleUseCase.invoke(googleSignInRequest).collect{resource->
                when(resource){

                    is Resource.Success->{
                        println("okey")
                    }
                    is Resource.Error->{
                        setEffect(LoginEffect.ShowErrorMessage(resource.message))
                    }
                }
            }


        }
    }
}


data class LoginUiState(
    val isLoading: Boolean  = false,
    val email: String = "",
    val password: String = ""
) : State

fun LoginUiState.isValid() : Boolean{
    return this.email.isNotBlank() && this.password.isNotBlank()
}
sealed interface LoginEffect : Effect{
    data class ShowErrorMessage(val message: String) : LoginEffect
    object NavigateToHome : LoginEffect
}

sealed interface LoginEvent : Event{

    data class OnEmailValueChange(val value: String) : LoginEvent
    data class OnPasswordValueChange(val value: String) : LoginEvent
    data class OnClickGoogleSignIn(val result: ActivityResult) : LoginEvent
    object OnClickLogin : LoginEvent
}
