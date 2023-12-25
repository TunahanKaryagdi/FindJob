package com.tunahankaryagdi.findjob.presentation.add

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.model.job.PostJobRequest
import com.tunahankaryagdi.findjob.data.model.job.Qualification
import com.tunahankaryagdi.findjob.data.model.job.dtos.JobDto
import com.tunahankaryagdi.findjob.domain.use_case.PostJobUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.utils.JobTypes
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddViewModel @Inject constructor(
    private val postJobUseCase: PostJobUseCase
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
            is AddEvent.OnNewQualificationValueChange->{
                setState(getCurrentState().copy(newQualification = event.value))
            }
            is AddEvent.OnClickJobType->{
                if (event.jobType != getCurrentState().selectedJobType){
                    setState(getCurrentState().copy(selectedJobType = event.jobType))
                }
            }
            is AddEvent.OnClickConfirmQualification->{
                val newQualifications = getCurrentState().qualifications
                newQualifications.add(getCurrentState().newQualification)
                setState(getCurrentState().copy(qualifications = newQualifications, isNewQualificationSectionOpen = false, newQualification = ""))
            }
            is AddEvent.OnClickCancelQualification->{
                setState(getCurrentState().copy(isNewQualificationSectionOpen = false))
            }
            is AddEvent.OnClickAddNewQualification->{
                setState(getCurrentState().copy(isNewQualificationSectionOpen = true))
            }
            is AddEvent.OnClickPost->{
                setState(getCurrentState().copy(isLoading = true))
                post()
                setState(getCurrentState().copy(isLoading = false))
            }

        }


    }


    private fun post(){
        val uiState = getCurrentState()
        if (
            uiState.title.isNotBlank() &&
            uiState.salary.isNotBlank() &&
            uiState.location.isNotBlank()){
            viewModelScope.launch {
                postJobUseCase.invoke(PostJobRequest(
                    "8147d1b4-ca17-4a33-bd2a-483a2789ef18",
                    uiState.location,
                    listOf(Qualification("Flutter")),
                    5000,
                    uiState.title,
                    JobTypes.FullTime.name,
                    "13d1b55e-fada-4ff9-a81d-d41a3fcfabaa"
                )).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            if (resource.data){
                                setEffect(AddEffect.ShowMessage("Successfully added"))
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
    val newQualification : String = "",
    val isNewQualificationSectionOpen : Boolean = false,
    val qualifications : MutableList<String> = mutableListOf(),
    val selectedJobType: JobTypes = JobTypes.FullTime
) : State

sealed interface AddEffect : Effect{
    data class ShowMessage(val message : String) : AddEffect

}


sealed interface AddEvent : Event{
    data class OnTitleValueChange(val value : String) : AddEvent
    data class OnSalaryValueChange(val value : String) : AddEvent
    data class OnLocationValueChange(val value : String) : AddEvent
    data class OnNewQualificationValueChange(val value : String)  :AddEvent
    data class OnClickJobType(val jobType: JobTypes) : AddEvent
    object OnClickAddNewQualification : AddEvent
    object OnClickConfirmQualification : AddEvent
    object OnClickCancelQualification : AddEvent
    object OnClickPost: AddEvent
}