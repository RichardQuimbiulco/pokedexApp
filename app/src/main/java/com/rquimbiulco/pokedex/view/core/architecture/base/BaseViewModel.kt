package com.rquimbiulco.pokedex.view.core.architecture.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenAction
import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenEvent
import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : ScreenState, Action : ScreenAction, Event : ScreenEvent>(
    initialState: State
) : ViewModel() {
    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<Event>()
    val uiEvent = _uiEvent.asSharedFlow()

    protected fun updateState(
        reducer: (State) -> State
    ) {
        _uiState.update(reducer)
    }

    protected suspend fun sendEvent(
        event: Event
    ) {
        _uiEvent.emit(event)
    }

    fun onAction(action: Action) {
        handleAction(action)
    }

    abstract fun handleAction(action: Action)

    protected fun launch(
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(block = block)
    }
}