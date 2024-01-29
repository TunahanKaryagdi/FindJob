package com.tunahankaryagdi.findjob.presentation.profile

import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.user.UserDetail
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
        when(event){
            is ProfileEvent.OnClickEditProfile->{
                setState(getCurrentState().copy(isBottomSheetVisible = false))
                setEffect(ProfileEffect.NavigateToEditProfile)
            }
            is ProfileEvent.OnBottomSheetValueChange->{
                setState(getCurrentState().copy(isBottomSheetVisible = event.value))
            }
            is ProfileEvent.OnClickLogout -> {
                logout()
            }
        }
    }
    private fun getUserById(){
        viewModelScope.launch {
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                getUserByIdUseCase.invoke(userId).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            resource.data.apply {
                                setState(getCurrentState().copy(userDetail = resource.data))
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

    private fun logout(){
        viewModelScope.launch {
            tokenStore.saveToken("")
            setEffect(ProfileEffect.NavigateToLogin)
        }
    }

}



data class ProfileUiState(
    val isLoading: Boolean = false,
    val userDetail: UserDetail? = null,
    val isBottomSheetVisible: Boolean = false
) : State

sealed interface ProfileEffect : Effect{
    data class ShowMessage(val message: String) : ProfileEffect
    object NavigateToEditProfile : ProfileEffect
    object NavigateToLogin : ProfileEffect

}

sealed interface ProfileEvent : Event{
    object OnClickEditProfile : ProfileEvent
    data class OnBottomSheetValueChange(val value: Boolean) : ProfileEvent
    object OnClickLogout : ProfileEvent

}