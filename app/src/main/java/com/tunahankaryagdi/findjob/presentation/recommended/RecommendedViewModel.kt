package com.tunahankaryagdi.findjob.presentation.recommended

import com.tunahankaryagdi.findjob.presentation.base.BaseViewModel
import com.tunahankaryagdi.findjob.presentation.base.Effect
import com.tunahankaryagdi.findjob.presentation.base.Event
import com.tunahankaryagdi.findjob.presentation.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RecommendedViewModel @Inject constructor() : BaseViewModel<RecommendedUiState,RecommendedEffect,RecommendedEvent>() {
    override fun setInitialState(): RecommendedUiState = RecommendedUiState()
    override fun handleEvents(event: RecommendedEvent) {
        when(event){
            is RecommendedEvent.OnClickTab->{
                setState(getCurrentState().copy(tabIndex = event.index))
            }
        }
    }
}


data class RecommendedUiState(
    val isLoading: Boolean = false,
    val tabIndex: Int = 0
) : State

sealed interface RecommendedEffect : Effect{

}

sealed interface RecommendedEvent : Event{
    data class OnClickTab(val index: Int) : RecommendedEvent
}