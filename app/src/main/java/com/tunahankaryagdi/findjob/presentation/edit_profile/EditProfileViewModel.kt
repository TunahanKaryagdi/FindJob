package com.tunahankaryagdi.findjob.presentation.edit_profile

import android.content.Context
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.model.preferrred_location.PostPreferredLocationRequest
import com.tunahankaryagdi.findjob.data.model.skill.PostSkillRequest
import com.tunahankaryagdi.findjob.data.model.user.CreateCompanyForUserRequest
import com.tunahankaryagdi.findjob.data.model.user.UpdateUserRequest
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.company.Company
import com.tunahankaryagdi.findjob.domain.model.company.CompanyStaff
import com.tunahankaryagdi.findjob.domain.model.user.UserDetail
import com.tunahankaryagdi.findjob.domain.use_case.company.GetCompaniesUseCase
import com.tunahankaryagdi.findjob.domain.use_case.preferred_location.PostPreferredLocationUseCase
import com.tunahankaryagdi.findjob.domain.use_case.skill.PostSkillUseCase
import com.tunahankaryagdi.findjob.domain.use_case.user.CreateCompanyForUserUseCase
import com.tunahankaryagdi.findjob.domain.use_case.user.GetCompaniesByUserIdUseCase
import com.tunahankaryagdi.findjob.domain.use_case.user.GetUserByIdUseCase
import com.tunahankaryagdi.findjob.domain.use_case.user.UpdateUserUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
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
    private val getCompaniesByUserIdUseCase: GetCompaniesByUserIdUseCase,
    private val getCompaniesUseCase: GetCompaniesUseCase,
    private val createCompanyForUserUseCase: CreateCompanyForUserUseCase,
    private val tokenStore: TokenStore,
    private val postPreferredLocationUseCase: PostPreferredLocationUseCase
) : BaseViewModel<EditProfileUiState,EditProfileEffect,EditProfileEvent>() {


    init {
        getUserById()
        getCompaniesByUserId()
    }

    override fun setInitialState(): EditProfileUiState  = EditProfileUiState()


    override fun handleEvents(event: EditProfileEvent) {
        when(event){
            is EditProfileEvent.OnClickEditSkill->{
                setState(getCurrentState().copy(isOpenSkillDialog = true))
            }
            is EditProfileEvent.OnClickEditExperience->{
                setState(getCurrentState().copy(isOpenExperienceDialog = true))
                getCompanies()
            }
            is EditProfileEvent.OnDismissDialog->{
                setState(getCurrentState().copy(
                    isOpenSkillDialog = false,
                    isOpenExperienceDialog = false,
                    isOpenLocationDialog = false,
                    selectedSkillValue = "",
                    selectedLocationValue = "",
                    selectedCompanyValue = null))
            }
            is EditProfileEvent.OnConfirmSkillDialog->{
                postSkill()
            }
            is EditProfileEvent.OnConfirmExperienceDialog ->{
                if (getCurrentState().selectedCompanyValue != null) createCompanyForUser()
            }
            is EditProfileEvent.OnClickSkillDropdownItem -> {
                setState(getCurrentState().copy(isExpandedDropdown = false, selectedSkillValue = event.dropdownItem.name))
            }
            is EditProfileEvent.OnClickExperienceDropdownItem -> {
                val company = getCurrentState().allCompanies.first { it.name == event.dropdownItem.name }
                setState(getCurrentState().copy(selectedCompanyValue = company, isExpandedDropdown = false))
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

            is EditProfileEvent.OnTitleValueChange -> {
                setState(getCurrentState().copy(titleValue = event.title))
            }

            is EditProfileEvent.OnClickEditPreferredLocations -> {
                setState(getCurrentState().copy(isOpenLocationDialog = true))
            }

            is EditProfileEvent.OnConfirmPreferredLocationDialog -> {
                postPreferredLocation()
            }

            is EditProfileEvent.OnClickPreferredLocationDropdownItem -> {
                setState(getCurrentState().copy(isExpandedDropdown = false, selectedLocationValue = event.dropdownItem.name))
            }

            is EditProfileEvent.OnRefresh -> {
                getUserById()
                getCompaniesByUserId()
            }
        }
    }


    private fun getUserById(){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true))
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                getUserByIdUseCase.invoke(userId).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            resource.data.apply {
                                setState(getCurrentState().copy(userDetail = this,isLoading = false))
                            }
                        }
                        is Resource.Error->{
                            setState(getCurrentState().copy(isLoading = false))
                            setEffect(EditProfileEffect.ShowMessage(resource.message))
                        }
                    }
                }
            }
        }
    }

    private fun postSkill(){
        viewModelScope.launch {
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                postSkillUseCase.invoke(
                    PostSkillRequest(
                        getCurrentState().selectedSkillValue,
                        userId,
                        getCurrentState().experienceValue.toInt()
                    )).collect{ resource   ->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(
                                isOpenSkillDialog = false,
                                selectedSkillValue = "",
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
                    updateUserUseCase.invoke(UpdateUserRequest(
                        id,
                        getCurrentState().userDetail?.nameSurname ?: "",
                        getCurrentState().userDetail?.email ?: "",),
                        it.toFile(context)
                    ).collect{resource->
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

    private fun getCompaniesByUserId(){
        viewModelScope.launch {
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                getCompaniesByUserIdUseCase.invoke(userId).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(companies = resource.data))
                        }
                        is Resource.Error->{
                            setEffect(EditProfileEffect.ShowMessage(resource.message))
                        }
                    }
                }
            }
        }
    }

    private fun getCompanies(){
        viewModelScope.launch {
            getCompaniesUseCase.invoke().collect{resource->
                when(resource){
                    is Resource.Success->{
                        setState(getCurrentState().copy(allCompanies = resource.data))
                    }
                    is Resource.Error->{
                        setEffect(EditProfileEffect.ShowMessage(resource.message))
                    }
                }
            }
        }
    }

    private fun createCompanyForUser(){
        viewModelScope.launch {
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                val createCompanyForUserRequest = CreateCompanyForUserRequest(userId,getCurrentState().selectedCompanyValue!!.id,getCurrentState().titleValue)
                createCompanyForUserUseCase.invoke(createCompanyForUserRequest).collect{ resource->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(
                                isOpenExperienceDialog = false,
                                selectedSkillValue = "",
                                titleValue = "",
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

    private fun postPreferredLocation(){
        viewModelScope.launch {
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                postPreferredLocationUseCase.invoke(PostPreferredLocationRequest(
                    userId = userId,
                    name = getCurrentState().selectedLocationValue
                )).collect{ resource   ->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(
                                isOpenLocationDialog = false,
                                selectedLocationValue = "",
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
}



data class EditProfileUiState(
    val userDetail: UserDetail? = null,
    val isLoading: Boolean = false,
    val selectedImage: Uri? = null,
    val companies: List<CompanyStaff> = emptyList(),
    val allCompanies: List<Company> = emptyList(),
    val isOpenSkillDialog: Boolean = false,
    val isOpenExperienceDialog: Boolean = false,
    val isOpenLocationDialog: Boolean = false,
    val isExpandedDropdown: Boolean = false,
    val selectedCompanyValue: Company? = null,
    val selectedSkillValue: String = "",
    val selectedLocationValue: String = "",
    val experienceValue: String = "",
    val titleValue: String = "",
) : State


sealed interface EditProfileEffect : Effect{
    data class ShowMessage(val message: String) : EditProfileEffect
}


sealed interface EditProfileEvent : Event{

    object OnRefresh : EditProfileEvent
    object OnClickEditSkill : EditProfileEvent
    object OnClickEditExperience : EditProfileEvent
    object OnClickEditPreferredLocations : EditProfileEvent
    object OnDismissDialog : EditProfileEvent
    object OnConfirmSkillDialog : EditProfileEvent
    object OnConfirmExperienceDialog : EditProfileEvent
    object OnConfirmPreferredLocationDialog : EditProfileEvent
    object OnDismissDropdown: EditProfileEvent
    data class OnChangeUri(val uri: Uri?,val context: Context) : EditProfileEvent
    data class OnClickSkillDropdownItem(val dropdownItem: DropdownItem) : EditProfileEvent
    data class OnClickExperienceDropdownItem(val dropdownItem: DropdownItem) : EditProfileEvent
    data class OnClickPreferredLocationDropdownItem(val dropdownItem: DropdownItem) : EditProfileEvent
    data class OnDropdownExpandedChange(val expanded: Boolean) : EditProfileEvent
    data class OnExperienceValueChange(val experience: String) : EditProfileEvent
    data class OnTitleValueChange(val title: String) : EditProfileEvent

}





