package com.tunahankaryagdi.findjob.presentation.recommended

import androidx.lifecycle.viewModelScope
import com.tunahankaryagdi.findjob.data.source.local.TokenStore
import com.tunahankaryagdi.findjob.domain.model.job.Job
import com.tunahankaryagdi.findjob.domain.use_case.recommended.GetRecommendedJobByAppliedUseCase
import com.tunahankaryagdi.findjob.domain.use_case.recommended.GetRecommendedJobByProfileUseCase
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
class RecommendedViewModel @Inject constructor(
    private val getRecommendedJobByAppliedUseCase: GetRecommendedJobByAppliedUseCase,
    private val getRecommendedJobByProfileUseCase: GetRecommendedJobByProfileUseCase,
    private val tokenStore: TokenStore
) : BaseViewModel<RecommendedUiState,RecommendedEffect,RecommendedEvent>() {
    override fun setInitialState(): RecommendedUiState = RecommendedUiState()

    init {
        getProfileRecommend()
    }
    override fun handleEvents(event: RecommendedEvent) {
        when(event){
            is RecommendedEvent.OnClickTab->{
                setState(getCurrentState().copy(tabIndex = event.index))
                when(event.index){
                    0 -> {
                        if (getCurrentState().profileRecommend.isEmpty()) getProfileRecommend()
                    }
                    1 -> {
                        if (getCurrentState().appliedRecommend.isEmpty()) getAppliedRecommend()
                    }
                }
            }

            is RecommendedEvent.OnRefresh ->{
                when(getCurrentState().tabIndex){
                    0 -> {
                        getProfileRecommend()
                    }
                    1 -> {
                        getAppliedRecommend()
                    }
                }
            }
        }
    }

    private fun getAppliedRecommend(){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true))
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                getRecommendedJobByAppliedUseCase.invoke(userId).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(appliedRecommend = resource.data, isLoading = false))
                        }
                        is Resource.Error->{
                        }
                    }
                }
            }
        }
    }
    private fun getProfileRecommend(){
        viewModelScope.launch {
            setState(getCurrentState().copy(isLoading = true))
            tokenStore.getToken().collect{
                val userId = JwtHelper.getUserId(it) ?: ""
                getRecommendedJobByProfileUseCase.invoke(userId).collect{resource->
                    when(resource){
                        is Resource.Success->{
                            setState(getCurrentState().copy(profileRecommend = resource.data, isLoading = false))
                        }
                        is Resource.Error->{
                        }
                    }
                }
            }
        }
    }
}


data class RecommendedUiState(
    val isLoading: Boolean = false,
    val tabIndex: Int = 0,
    val profileRecommend: List<Job> = emptyList(),
    val appliedRecommend: List<Job> = emptyList(),
) : State

sealed interface RecommendedEffect : Effect{

}

sealed interface RecommendedEvent : Event{
    data class OnClickTab(val index: Int) : RecommendedEvent
    object OnRefresh : RecommendedEvent
}