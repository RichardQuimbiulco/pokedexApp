package com.rquimbiulco.pokedex.view.auth.splash

import androidx.lifecycle.viewModelScope
import com.rquimbiulco.pokedex.domain.repository.SessionRepository
import com.rquimbiulco.pokedex.view.core.architecture.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val sessionRepository: SessionRepository) :
    BaseViewModel<SplashState, SplashAction, SplashEvent>(initialState = SplashState()) {

    init {
        checkSession()
    }

    fun checkSession() {
        viewModelScope.launch {
            sessionRepository.isLoggedIn().collect { logged ->
                if (logged) {
                    sendEvent(SplashEvent.NavigateToPokedex)
                } else {
                    sendEvent(SplashEvent.NavigateToLogin)
                }
            }

        }
    }

    override fun handleAction(action: SplashAction) {
        // No actions
    }

}