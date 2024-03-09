package com.tunahankaryagdi.findjob.presentation.add_company

import android.content.Context
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.model.company.PostCompanyRequest
import com.tunahankaryagdi.findjob.domain.use_case.company.PostCompanyUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.utils.FileHelper.toFile
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddCompanyViewModel @Inject constructor(
    private val postCompanyUseCase: PostCompanyUseCase
): BaseViewModel<AddCompanyUiState,AddCompanyEffect,AddCompanyEvent>(){
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
                postCompany(event.context)
            }
        }
    }

    private fun postCompany(context: Context){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true,))
            val state = getCurrentState()
            postCompanyUseCase.invoke(PostCompanyRequest(state.name,state.selectedImage?.toFile(context))).collect{ resource->
                when(resource){
                    is Resource.Success->{
                        setState(getCurrentState().copy(isLoading = false, selectedImage = null, name = ""))
                    }
                    is Resource.Error->{
                        setState(getCurrentState().copy(isLoading = false))
                        setEffect(AddCompanyEffect.ShowMessage(resource.message))
                    }
                }
            }
        }
    }
}

data class AddCompanyUiState(
    val isLoading: Boolean = false,
    val selectedImage: Uri? = null,
    val name: String = "",
) : State

sealed interface AddCompanyEffect : Effect{
    data class ShowMessage(val message: String) : AddCompanyEffect
}

sealed interface AddCompanyEvent : Event{
    data class OnUriChange(val uri: Uri) : AddCompanyEvent
    data class OnNameValueChange(val name: String) : AddCompanyEvent
    data class OnClickPost(val context: Context) : AddCompanyEvent
}