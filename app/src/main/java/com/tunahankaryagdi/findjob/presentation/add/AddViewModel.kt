package com.tunahankaryagdi.findjob.presentation.add

import android.util.Log
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.utils.JobTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddViewModel @Inject constructor(): BaseViewModel<AddUiState,AddEffect,AddEvent>() {


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
                val jobTypes = getCurrentState().selectedJobTypes
                if (event.isSelected){
                    jobTypes.add(event.jobType)
                    setState(getCurrentState().copy(selectedJobTypes = jobTypes ))
                }
                else{
                    jobTypes.remove(event.jobType)
                    setState(getCurrentState().copy(selectedJobTypes = jobTypes ))
                }
                println(getCurrentState().selectedJobTypes)
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
    val selectedJobTypes : HashSet<JobTypes> = hashSetOf()
) : State

sealed interface AddEffect : Effect{
    data class ShowMessage(val message : String) : AddEffect
}


sealed interface AddEvent : Event{
    data class OnTitleValueChange(val value : String) : AddEvent
    data class OnSalaryValueChange(val value : String) : AddEvent
    data class OnLocationValueChange(val value : String) : AddEvent
    data class OnNewQualificationValueChange(val value : String)  :AddEvent
    data class OnClickJobType(val isSelected: Boolean,val jobType: JobTypes) : AddEvent
    object OnClickAddNewQualification : AddEvent
    object OnClickConfirmQualification : AddEvent
    object OnClickCancelQualification :AddEvent
}