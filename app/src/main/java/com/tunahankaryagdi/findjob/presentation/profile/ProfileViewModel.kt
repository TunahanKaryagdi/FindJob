package com.tunahankaryagdi.findjob.presentation.profile

import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.user.Skill
import com.tunahankaryagdi.findjob.domain.use_case.GetUserByIdUseCase
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
class ProfileViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val tokenStore: TokenStore
) : BaseViewModel<ProfileUiState,ProfileEffect,ProfileEvent>() {

    init {
        getUserById()
    }
    override fun setInitialState(): ProfileUiState = ProfileUiState()

    override fun handleEvents(event: ProfileEvent) {
    }

    private fun getUserById(){
        viewModelScope.launch {

            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                getUserByIdUseCase.invoke(userId).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            resource.data.apply {
                                setState(getCurrentState().copy(name = this.nameSurname, email = this.email, skills = this.skills))
                            }
                        }
                        is Resource.Error->{
                            setEffect(ProfileEffect.ShowMessage(resource.message))
                        }
                    }
                }
            }
        }
    }
}



data class ProfileUiState(
    val isLoading: Boolean = false,
    val name: String = "",
    val email: String = "",
    val skills: List<Skill> = emptyList()
) : State

sealed interface ProfileEffect : Effect{
    data class ShowMessage(val message: String) : ProfileEffect

}

sealed interface ProfileEvent : Event{
}