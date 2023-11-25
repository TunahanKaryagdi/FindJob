package com.tunahankaryagdi.findjob.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE: State,EFFECT: Effect, EVENT: Event> : ViewModel(){

    private val initialState : STATE by lazy { setInitialState()  }

    abstract fun setInitialState() : STATE

    private val _state : MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state = _state

    private val _effect : MutableSharedFlow<EFFECT> = MutableSharedFlow()
    val effect = _effect

    private val _event : MutableSharedFlow<EVENT> = MutableSharedFlow()
    val event = _event

    abstract fun handleEvents(event: EVENT)

    fun getCurrentState() : STATE = _state.value

    //subscribe event

    fun setState(state: STATE){
        viewModelScope.launch { _state.emit(state) }
    }

    fun setEffect(effect: EFFECT){
        viewModelScope.launch { _effect.emit(effect) }
    }

    fun setEvent(event: EVENT){
        viewModelScope.launch { _event.emit(event) }
    }
}



interface State
interface Effect
interface Event