package com.tunahankaryagdi.findjob.presentation.add

import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.model.job.PostJobRequest
import com.tunahankaryagdi.findjob.data.model.job.Qualification
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.company.Company
import com.tunahankaryagdi.findjob.domain.use_case.job.PostJobUseCase
import com.tunahankaryagdi.findjob.domain.use_case.user.GetCurrentCompanyByUserIdUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.utils.DropdownItem
import com.tunahankaryagdi.findjob.utils.JobType
import com.tunahankaryagdi.findjob.utils.JwtHelper
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddViewModel @Inject constructor(
    private val postJobUseCase: PostJobUseCase,
    private val getCurrentCompanyByUserIdUseCase: GetCurrentCompanyByUserIdUseCase,
    private val tokenStore: TokenStore
): BaseViewModel<AddUiState,AddEffect,AddEvent>() {


    override fun setInitialState(): AddUiState = AddUiState()

    override fun handleEvents(event: AddEvent) {

        when(event){
            is AddEvent.OnTitleValueChange->{
                setState(getCurrentState().copy(title = event.value))
            }
            is AddEvent.OnLocationValueChange->{
                setState(getCurrentState().copy(location = event.value))
            }
            is AddEvent.OnSalaryValueChange->{
                setState(getCurrentState().copy(salary = event.value))
            }
            is AddEvent.OnExperienceValueChange->{
                setState(getCurrentState().copy(experienceValue = event.value))
            }
            is AddEvent.OnClickJobType->{
                if (event.jobType != getCurrentState().selectedJobType){
                    setState(getCurrentState().copy(selectedJobType = event.jobType))
                }
            }
            is AddEvent.OnClickEdit->{
                setState(getCurrentState().copy(isOpenDialog = true))
            }
            is AddEvent.OnConfirmDialog->{
                val newQualifications = getCurrentState().qualifications
                newQualifications.add(Qualification(getCurrentState().qualification,getCurrentState().experienceValue.toInt()))
                setState(getCurrentState().copy(qualifications = newQualifications, isOpenDialog = false, qualification = "", experienceValue = ""))
            }
            is AddEvent.OnDismissDialog->{
                setState(getCurrentState().copy(isOpenDialog = false, qualification = ""))
            }
            is AddEvent.OnClickPost->{

                setState(getCurrentState().copy(isLoading = true))
                getCompanyInfo()
                setState(getCurrentState().copy(isLoading = false))
            }

            is AddEvent.OnClickDropdownItem -> {
                setState(getCurrentState().copy(isExpandedDropdown = false, qualification = event.item.name))
            }
            is AddEvent.OnDismissDropdown -> {
                setState(getCurrentState().copy(isExpandedDropdown = false))
            }
            is AddEvent.OnDropdownExpandedChange -> {
                setState(getCurrentState().copy(isExpandedDropdown = event.expanded))
            }
        }


    }

    private fun getCompanyInfo(){
        viewModelScope.launch {
            tokenStore.getToken().collect{token->
                val userId = JwtHelper.getUserId(token) ?: ""
                getCurrentCompanyByUserIdUseCase.invoke(userId).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(companyInfo = resource.data?.company))
                            post()
                        }
                        is Resource.Error->{}

                    }
                }
            }
        }
    }


    private fun post(){

        val uiState = getCurrentState()
        if (
            uiState.companyInfo != null &&
            uiState.title.isNotBlank() &&
            uiState.salary.isNotBlank() &&
            uiState.location.isNotBlank()){
            viewModelScope.launch {
                tokenStore.getToken().collect{token->
                    val userId = JwtHelper.getUserId(token) ?: ""
                    postJobUseCase.invoke(PostJobRequest(
                        uiState.companyInfo.id,
                        uiState.location,
                        uiState.qualifications,
                        uiState.salary.toInt(),
                        uiState.title,
                        uiState.selectedJobType.name,
                        userId
                    )).collect{resource->
                        when(resource){
                            is Resource.Success->{
                                if (resource.data){
                                    setEffect(AddEffect.ShowMessage("Successfully added"))
                                    setState(AddUiState())
                                }
                                else{
                                    setEffect(AddEffect.ShowMessage("Failed"))
                                }
                            }
                            is Resource.Error->{
                                setEffect(AddEffect.ShowMessage(resource.message))
                            }
                        }
                    }
                }
            }
        }
        else{
            setEffect(AddEffect.ShowMessage("fill in the required fields"))
        }
    }

}



data class AddUiState(
    val isLoading : Boolean = false,
    val title : String = "",
    val location: String = "",
    val salary: String = "",
    val qualification : String = "",
    val isOpenDialog : Boolean = false,
    val qualifications : MutableList<Qualification> = mutableListOf(),
    val selectedJobType: JobType = JobType.FullTime,
    val experienceValue: String = "",
    val isExpandedDropdown: Boolean = false,
    val companyInfo: Company? = null
) : State

sealed interface AddEffect : Effect{
    data class ShowMessage(val message : String) : AddEffect

}


sealed interface AddEvent : Event{
    data class OnTitleValueChange(val value : String) : AddEvent
    data class OnSalaryValueChange(val value : String) : AddEvent
    data class OnLocationValueChange(val value : String) : AddEvent
    data class OnExperienceValueChange(val value : String)  :AddEvent
    data class OnClickJobType(val jobType: JobType) : AddEvent
    object OnClickEdit : AddEvent
    object OnDismissDialog : AddEvent
    object OnConfirmDialog : AddEvent
    object OnDismissDropdown: AddEvent
    data class OnClickDropdownItem(val item: DropdownItem) : AddEvent
    data class OnDropdownExpandedChange(val expanded: Boolean) : AddEvent
    object OnClickPost: AddEvent

}