package com.tunahankaryagdi.findjob.presentation.login


import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.tunahankaryagdi.findjob.data.model.user.GoogleSignInRequest
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
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : BaseViewModel<LoginUiState,LoginEffect,LoginEvent>(){
    override fun setInitialState(): LoginUiState = LoginUiState()

    override fun handleEvents(event: LoginEvent) {
        when(event){
            is LoginEvent.OnClickGoogleSignIn->{
                handleGoogleSignInResult(event.result)
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
    val isLoading : Boolean  = false,
) : State

sealed interface LoginEffect : Effect{
    data class ShowErrorMessage(val message: String) : LoginEffect
}

sealed interface LoginEvent : Event{
    data class OnClickGoogleSignIn(val result: ActivityResult) : LoginEvent
}
