package com.tunahankaryagdi.findjob.presentation.edit_profile

import android.content.Context
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.model.skill.PostSkillRequest
import com.tunahankaryagdi.findjob.data.model.user.UpdateUserRequest
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.user.Skill
import com.tunahankaryagdi.findjob.domain.use_case.GetUserByIdUseCase
import com.tunahankaryagdi.findjob.domain.use_case.PostSkillUseCase
import com.tunahankaryagdi.findjob.domain.use_case.UpdateUserUseCase
import com.tunahankaryagdi.findjob.presentation.add.AddEvent
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.presentation.profile.ProfileEffect
import com.tunahankaryagdi.findjob.utils.DropdownItem
import com.tunahankaryagdi.findjob.utils.FileHelper.toFile
import com.tunahankaryagdi.findjob.utils.JwtHelper
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val postSkillUseCase: PostSkillUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val tokenStore: TokenStore
) : BaseViewModel<EditProfileUiState,EditProfileEffect,EditProfileEvent>() {


    init {
        getUserById()
    }

    override fun setInitialState(): EditProfileUiState  = EditProfileUiState()


    override fun handleEvents(event: EditProfileEvent) {
        when(event){
            is EditProfileEvent.OnClickEdit->{
                setState(getCurrentState().copy(isOpenDialog = true))
            }
            is EditProfileEvent.OnDismissDialog->{
                setState(getCurrentState().copy(isOpenDialog = false, selectedDropdownValue = ""))
            }
            is EditProfileEvent.OnConfirmDialog->{
                postNewSkill()
            }
            is EditProfileEvent.OnClickDropdownItem -> {
                setState(getCurrentState().copy(isExpandedDropdown = false, selectedDropdownValue = event.dropdownItem.name))
            }
            is EditProfileEvent.OnDismissDropdown -> {
               setState(getCurrentState().copy(isExpandedDropdown = false))
            }

            is EditProfileEvent.OnDropdownExpandedChange ->{
                setState(getCurrentState().copy(isExpandedDropdown = event.expanded))
            }

            is EditProfileEvent.OnExperienceValueChange ->{
                setState(getCurrentState().copy(experienceValue = event.experience))
            }

            is EditProfileEvent.OnChangeUri -> {
                setState(getCurrentState().copy(selectedImage = event.uri))
                postImage(event.context)
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
                                setState(getCurrentState().copy(name = this.nameSurname, email = this.email, skills = this.skills, image = this.image))
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
                postSkillUseCase.invoke(PostSkillRequest(getCurrentState().selectedDropdownValue,userId,getCurrentState().experienceValue.toInt())).collect{ resource   ->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(
                                isOpenDialog = false,
                                selectedDropdownValue = "",
                                experienceValue = "",
                                isExpandedDropdown = false
                            ))
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

    private fun postImage(context: Context){
        viewModelScope.launch {
            tokenStore.getToken().collect{token->
                val id = JwtHelper.getUserId(token) ?: ""
                getCurrentState().selectedImage?.let {
                    updateUserUseCase.invoke(UpdateUserRequest(id,getCurrentState().name,getCurrentState().email),it.toFile(context)).collect{resource->
                        when(resource){
                            is Resource.Success->{
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

}



data class EditProfileUiState(
    val isLoading: Boolean = false,
    val name: String = "",
    val email: String = "",
    val image: String = "",
    val selectedImage: Uri? = null,
    val skills: List<Skill> = emptyList(),
    val isOpenDialog: Boolean = false,
    val selectedDropdownValue: String = "",
    val experienceValue: String = "",
    val isExpandedDropdown: Boolean = false,

    ) : State


sealed interface EditProfileEffect : Effect{
    data class ShowMessage(val message: String) : EditProfileEffect
}


sealed interface EditProfileEvent : Event{

    object OnClickEdit : EditProfileEvent
    object OnDismissDialog : EditProfileEvent
    object OnConfirmDialog : EditProfileEvent
    object OnDismissDropdown: EditProfileEvent
    data class OnChangeUri(val uri: Uri?,val context: Context) : EditProfileEvent
    data class OnClickDropdownItem(val dropdownItem: DropdownItem) : EditProfileEvent
    data class OnDropdownExpandedChange(val expanded: Boolean) : EditProfileEvent
    data class OnExperienceValueChange(val experience: String) : EditProfileEvent

}





