package com.rquimbiulco.pokedex.view.auth.login

import com.rquimbiulco.pokedex.domain.model.UserMode
import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenState

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val userType: UserMode = UserMode.TRAINER,
    val isLoading: Boolean = false,
    val isLoginEnabled: Boolean = false,
    val expanded: Boolean = false,
) : ScreenState
