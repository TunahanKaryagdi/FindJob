package com.tunahankaryagdi.findjob.presentation.home

import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.use_case.job.GetJobsUseCase
import com.tunahankaryagdi.findjob.domain.use_case.user.GetUserByIdUseCase
import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
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
        getUser()
    }

    override fun setInitialState(): HomeUiState  = HomeUiState()

    override fun handleEvents(event: HomeEvent) {
        when(event){
            is HomeEvent.OnRefresh->{
                setState(getCurrentState().copy(page = 1, filteredJobs = emptyList(), jobs = emptyList()))
                getJobs()
                getUser()
            }
            is HomeEvent.OnSearchValueChange -> {
                setState(getCurrentState().copy(searchText = event.text))
                val filteredJobs = getCurrentState().jobs.filter {
                    it.title.contains(getCurrentState().searchText)
                }
                setState(getCurrentState().copy(filteredJobs = filteredJobs))
            }
            is HomeEvent.OnGetNextPage -> {
                setState(getCurrentState().copy(page = event.page + 1))
                getJobs()
            }
        }
    }

    private fun getJobs(){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true))
            getJobsUseCase(getCurrentState().page).collect{resource->
                when(resource){
                    is Resource.Success->{

                        val currentJobs = getCurrentState().jobs.toMutableList()
                        currentJobs.addAll(resource.data)
                        setState(getCurrentState().copy(isLoading = false, jobs = currentJobs, filteredJobs = currentJobs))
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
    val filteredJobs: List<Job> = emptyList(),
    val userImage: String? = null,
    val searchText: String = "",
    val page: Int = 1
) : State

sealed interface HomeEffect : Effect{
    data class ShowErrorMessage(val message: String) : HomeEffect

}

sealed interface HomeEvent : Event{
    object OnRefresh : HomeEvent
    data class OnSearchValueChange(val text: String) : HomeEvent
    data class OnGetNextPage(val page: Int) : HomeEvent
}
