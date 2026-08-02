package com.rquimbiulco.pokedex.view.auth.splash

import com.rquimbiulco.pokedex.domain.repository.SessionRepository
import com.rquimbiulco.pokedex.view.core.architecture.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val sessionRepository: SessionRepository) :
    BaseViewModel<SplashUiState, SplashUiAction, SplashUiEvent>(initialState = SplashUiState) {

    init {
        checkSession()
    }

    fun checkSession() {
        launch {
            val isLogged = sessionRepository.isLoggedIn().first()
            if (isLogged) {
                sendEvent(SplashUiEvent.NavigateToPokedex)
            } else {
                sendEvent(SplashUiEvent.NavigateToLogin)
            }
        }

    }

    override fun handleAction(action: SplashUiAction) = Unit
}