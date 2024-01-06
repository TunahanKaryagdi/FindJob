package com.tunahankaryagdi.findjob.presentation.edit_profile

import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.model.skill.PostSkillRequest
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.user.Skill
import com.tunahankaryagdi.findjob.domain.use_case.GetUserByIdUseCase
import com.tunahankaryagdi.findjob.domain.use_case.PostSkillUseCase
import com.tunahankaryagdi.findjob.presentation.add.AddEvent
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.presentation.profile.ProfileEffect
import com.tunahankaryagdi.findjob.utils.JwtHelper
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val postSkillUseCase: PostSkillUseCase,
    private val tokenStore: TokenStore
) : BaseViewModel<EditProfileUiState,EditProfileEffect,EditProfileEvent>() {


    init {
        getUserById()
    }

    override fun setInitialState(): EditProfileUiState  = EditProfileUiState()


    override fun handleEvents(event: EditProfileEvent) {
        when(event){
            is EditProfileEvent.OnSkillValueChange->{
                setState(getCurrentState().copy(skill = event.value))
            }
            is EditProfileEvent.OnClickEdit->{
                setState(getCurrentState().copy(isOpenDialog = true))
            }
            is EditProfileEvent.OnDismissDialog->{
                setState(getCurrentState().copy(isOpenDialog = false, skill = ""))
            }
            is EditProfileEvent.OnConfirmDialog->{
                postNewSkill()
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
                                setState(getCurrentState().copy(name = this.nameSurname, email = this.email, skills = this.skills))
                            }
                        }
                        is Resource.Error->{
                            setEffect(EditProfileEffect.ShowMessage(resource.message))
                        }
                    }
                }
            }
        }
    }

    private fun postNewSkill(){
        viewModelScope.launch {
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                postSkillUseCase.invoke(PostSkillRequest(getCurrentState().skill,userId)).collect{ resource->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(isOpenDialog = false, skill = ""))
                            getUserById()
                        }
                        is Resource.Error->{
                            setEffect(EditProfileEffect.ShowMessage(resource.message))
                        }
                    }
                }
            }
        }
    }

}



data class EditProfileUiState(
    val isLoading: Boolean = false,
    val name: String = "",
    val email: String = "",
    val skills: List<Skill> = emptyList(),
    val skill: String = "",
    val isOpenDialog: Boolean = false
) : State


sealed interface EditProfileEffect : Effect{
    data class ShowMessage(val message: String) : EditProfileEffect
}


sealed interface EditProfileEvent : Event{

    data class OnSkillValueChange(val value : String) : EditProfileEvent
    object OnClickEdit : EditProfileEvent
    object OnDismissDialog : EditProfileEvent
    object OnConfirmDialog : EditProfileEvent

}





