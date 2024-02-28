package com.tunahankaryagdi.findjob.presentation.add_company

import android.net.Uri
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddCompanyViewModel @Inject constructor(): BaseViewModel<AddCompanyUiState,AddCompanyEffect,AddCompanyEvent>(){
    override fun setInitialState(): AddCompanyUiState = AddCompanyUiState()

    override fun handleEvents(event: AddCompanyEvent) {
        when(event){
            is AddCompanyEvent.OnUriChange -> {
                setState(getCurrentState().copy(selectedImage = event.uri))
            }
            is AddCompanyEvent.OnNameValueChange -> {
                setState(getCurrentState().copy(name = event.name))
            }
            is AddCompanyEvent.OnClickPost -> {

            }
        }
    }


}

data class AddCompanyUiState(
    val isLoading: Boolean = false,
    val selectedImage: Uri? = null,
    val name: String = ""
) : State

sealed interface AddCompanyEffect : Effect{

}

sealed interface AddCompanyEvent : Event{
    data class OnUriChange(val uri: Uri) : AddCompanyEvent
    data class OnNameValueChange(val name: String) : AddCompanyEvent
    object OnClickPost : AddCompanyEvent
}