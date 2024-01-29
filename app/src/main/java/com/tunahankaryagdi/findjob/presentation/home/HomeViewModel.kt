package com.tunahankaryagdi.findjob.presentation.home

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.use_case.GetJobsUseCase
import com.tunahankaryagdi.findjob.domain.use_case.GetUserByIdUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.presentation.home.components.DrawerItem
import com.tunahankaryagdi.findjob.utils.DrawerItemTitle
import com.tunahankaryagdi.findjob.utils.JwtHelper
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getJobsUseCase: GetJobsUseCase,
    private val tokenStore: TokenStore,
    private val getUserByIdUseCase: GetUserByIdUseCase
) : BaseViewModel<HomeUiState,HomeEffect,HomeEvent>(){

    init {
        getJobs()
    }

    override fun setInitialState(): HomeUiState  = HomeUiState()

    override fun handleEvents(event: HomeEvent) {

    }

    fun getJobs(){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true))
            getJobsUseCase(1).collect{resource->
                when(resource){
                    is Resource.Success->{
                        setState(getCurrentState().copy(isLoading = false, jobs = resource.data))
                    }
                    is Resource.Error->{
                        setState(getCurrentState().copy(isLoading = false,))
                        setEffect(HomeEffect.ShowErrorMessage(resource.message))
                    }
                }
            }
        }
    }

    private fun getUser(){
        viewModelScope.launch {
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                getUserByIdUseCase.invoke(userId).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            if (resource.data.image != null){
                                setState(getCurrentState().copy(userImage = resource.data.image))
                            }
                        }
                        is Resource.Error->{
                            setEffect(HomeEffect.ShowErrorMessage(resource.message))
                        }
                    }
                }
            }
        }
    }


}


data class HomeUiState(
    val isLoading: Boolean = false,
    val jobs: List<Job> = emptyList(),
    val userImage: String? = null

) : State

sealed interface HomeEffect : Effect{
    data class ShowErrorMessage(val message: String) : HomeEffect


}

sealed interface HomeEvent : Event{

}
