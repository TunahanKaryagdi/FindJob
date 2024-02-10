package com.tunahankaryagdi.findjob.presentation.application

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.model.application.UpdateApplicationRequest
import com.tunahankaryagdi.findjob.domain.model.application.Application
import com.tunahankaryagdi.findjob.domain.model.user.UserDetail
import com.tunahankaryagdi.findjob.domain.use_case.application.GetApplicationsByJobIdUseCase
import com.tunahankaryagdi.findjob.domain.use_case.user.GetUserByIdUseCase
import com.tunahankaryagdi.findjob.domain.use_case.application.UpdateApplicationByIdUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.utils.DropdownItem
import com.tunahankaryagdi.findjob.utils.Resource
import com.tunahankaryagdi.findjob.utils.applicationStatuses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ApplicationViewModel @Inject constructor(
    private val getApplicationsByJobIdUseCase: GetApplicationsByJobIdUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val updateApplicationByIdUseCase: UpdateApplicationByIdUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ApplicationUiState,ApplicationEffect,ApplicationEvent>(){

    private val jobId: String

    init {
        jobId = savedStateHandle.get<String>("jobId") ?: ""
        getApplicationsByJobId()
    }

    override fun setInitialState(): ApplicationUiState  = ApplicationUiState()

    override fun handleEvents(event: ApplicationEvent) {

        when(event){
            is ApplicationEvent.OnClickUser->{
                getUserByUserId(event.application)
            }
            is ApplicationEvent.OnDismissDialog->{
                setState(getCurrentState().copy(isOpenDialog = false))
            }
            is ApplicationEvent.OnConfirmDialog->{
                confirmApplication()
            }
            is ApplicationEvent.OnClickDropdownItem -> {
                setState(getCurrentState().copy(isExpandedDropdown = false))
                filterApplications(event.item)
            }
            is ApplicationEvent.OnDismissDropdown ->{
                setState(getCurrentState().copy(isExpandedDropdown = false))
            }
            is ApplicationEvent.OnDropdownExpandedChange -> {
                setState(getCurrentState().copy(isExpandedDropdown = event.expanded))
            }
        }
    }

    private fun getApplicationsByJobId(){
        viewModelScope.launch {
            getApplicationsByJobIdUseCase.invoke(jobId).collect{resource->
                when(resource){
                    is Resource.Success->{
                        setState(getCurrentState().copy(applications = resource.data, isLoading = false, filteredApplications = resource.data))
                    }
                    is Resource.Error->{
                        setState(getCurrentState().copy(isLoading = false))
                    }
                }
            }
        }
    }

    private fun getUserByUserId(application: Application){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true, isOpenDialog = true))
            getUserByIdUseCase.invoke(application.user.id).collect{resource->
                when(resource){
                    is Resource.Success->{
                        setState(getCurrentState().copy(selectedUser = resource.data, isLoading = false, selectedApplication = application  ))
                    }
                    is Resource.Error->{
                        setState(getCurrentState().copy(isLoading = false))
                    }
                }
            }

        }
    }

    private fun confirmApplication(){
        viewModelScope.launch {
            val application = getCurrentState().selectedApplication!!
            updateApplicationByIdUseCase.invoke(UpdateApplicationRequest(application.id,true)).collect{ resource->
                when(resource){
                    is Resource.Success->{
                        setState(getCurrentState().copy(isOpenDialog = false))
                        getApplicationsByJobId()
                    }
                    is Resource.Error->{
                        setState(getCurrentState().copy(isLoading = false))
                    }
                }
            }
        }
    }

    private fun filterApplications(dropdownItem: DropdownItem){
        when(dropdownItem.id){
            1->{
                setState(getCurrentState().copy(filteredApplications = getCurrentState().applications, selectedFilter = dropdownItem))
            }
            2->{
                setState(getCurrentState().copy(filteredApplications = getCurrentState().applications.filter { !it.status },selectedFilter = dropdownItem))
            }
            3->{
                setState(getCurrentState().copy(filteredApplications = getCurrentState().applications.filter { it.status },selectedFilter = dropdownItem))
            }
        }
    }
}

data class ApplicationUiState(
    val isLoading: Boolean = false,
    val applications: List<Application> = emptyList(),
    val filteredApplications: List<Application> = emptyList(),
    val selectedUser: UserDetail? = null,
    val isOpenDialog: Boolean = false,
    val selectedApplication: Application? = null,
    val isExpandedDropdown: Boolean = false,
    val selectedFilter: DropdownItem = applicationStatuses[0]

) : State

sealed interface ApplicationEffect : Effect {
    data class ShowMessage(val message: String) : ApplicationEffect
}

sealed interface ApplicationEvent : Event {

    data class OnClickUser(val application: Application) : ApplicationEvent
    object OnDismissDialog : ApplicationEvent
    object OnConfirmDialog : ApplicationEvent
    object OnDismissDropdown: ApplicationEvent
    data class OnClickDropdownItem(val item: DropdownItem) : ApplicationEvent
    data class OnDropdownExpandedChange(val expanded: Boolean) : ApplicationEvent
}

