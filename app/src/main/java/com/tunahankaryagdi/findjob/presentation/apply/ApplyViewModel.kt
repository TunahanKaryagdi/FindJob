package com.tunahankaryagdi.findjob.presentation.apply

import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ApplyViewModel @Inject constructor(): BaseViewModel<ApplyUiState,ApplyEffect,ApplyEvent>() {
    override fun setInitialState(): ApplyUiState = ApplyUiState()

    override fun handleEvents(event: ApplyEvent) {

        when(event){
            is ApplyEvent.OnEmailValueChange->{
                setState(getCurrentState().copy(email = event.value))
            }
            is ApplyEvent.OnNameValueChange->{
                setState(getCurrentState().copy(nameSurname = event.value))
            }
            is ApplyEvent.OnMessageValueChange->{
                setState(getCurrentState().copy(message = event.value))
            }
            is ApplyEvent.OnNewQualificationValueChange->{
                setState(getCurrentState().copy(newQualification = event.value))
            }
            is ApplyEvent.OnClickAddNewQualification->{
                setState(getCurrentState().copy(isNewQualificationSectionOpen = true))
            }
            is ApplyEvent.OnClickConfirmQualification->{
                val newQualifications = getCurrentState().qualifications
                newQualifications.add(getCurrentState().newQualification)
                setState(getCurrentState().copy(qualifications = newQualifications, isNewQualificationSectionOpen = false, newQualification = ""))
            }
            is ApplyEvent.OnClickCancelQualification->{
                setState(getCurrentState().copy(isNewQualificationSectionOpen = false))
            }
            is ApplyEvent.OnClickApply->{

            }
        }

    }
}


data class ApplyUiState(
    val isLoading: Boolean = false,
    val nameSurname: String = "",
    val email: String = "",
    val message: String = "",
    val newQualification : String = "",
    val isNewQualificationSectionOpen : Boolean = false,
    val qualifications : MutableList<String> = mutableListOf(),
) : State

sealed interface ApplyEffect : Effect{

}

sealed interface ApplyEvent : Event{
    data class OnNameValueChange(val value : String) : ApplyEvent
    data class OnEmailValueChange(val value : String) : ApplyEvent
    data class OnMessageValueChange(val value : String) : ApplyEvent
    data class OnNewQualificationValueChange(val value : String)  :ApplyEvent
    object OnClickAddNewQualification : ApplyEvent
    object OnClickConfirmQualification : ApplyEvent
    object OnClickCancelQualification : ApplyEvent
    object OnClickApply : ApplyEvent
}