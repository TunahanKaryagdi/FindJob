package com.tunahankaryagdi.findjob.presentation.home

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.use_case.GetJobsUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import com.tunahankaryagdi.findjob.presentation.home.components.DrawerItem
import com.tunahankaryagdi.findjob.utils.DrawerItemTitle
import com.tunahankaryagdi.findjob.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getJobsUseCase: GetJobsUseCase,
    private val tokenStore: TokenStore
) : BaseViewModel<HomeUiState,HomeEffect,HomeEvent>(){

    init {
        getJobs()
    }

    override fun setInitialState(): HomeUiState  = HomeUiState()

    override fun handleEvents(event: HomeEvent) {
        when(event){

            is HomeEvent.OnClickDrawerItem->{
                when(event.drawerItem.id){
                    DrawerItemTitle.Profile->{
                        setEffect(HomeEffect.NavigateToProfile)
                    }
                    DrawerItemTitle.Applications->{
                        setEffect(HomeEffect.NavigateToApplications)

                    }
                    DrawerItemTitle.Jobs->{
                        setEffect(HomeEffect.NavigateToJobs)

                    }
                    DrawerItemTitle.Logout->{
                        logout()
                        setEffect(HomeEffect.NavigateToLogin)
                    }
                }
            }

        }
    }

    private fun getJobs(){
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

    private fun logout(){
        viewModelScope.launch {
            tokenStore.saveToken("")
        }
    }

}


data class HomeUiState(
    val isLoading: Boolean = false,
    val jobs: List<Job> = emptyList(),
) : State

sealed interface HomeEffect : Effect{
    data class ShowErrorMessage(val message: String) : HomeEffect
    object NavigateToProfile : HomeEffect
    object NavigateToLogin : HomeEffect
    object NavigateToApplications : HomeEffect
    object NavigateToJobs : HomeEffect

}

sealed interface HomeEvent : Event{
    data class OnClickDrawerItem(val drawerItem: DrawerItem) : HomeEvent
}
