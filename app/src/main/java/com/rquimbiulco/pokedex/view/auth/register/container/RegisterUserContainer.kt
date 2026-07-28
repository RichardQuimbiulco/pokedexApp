package com.rquimbiulco.pokedex.view.auth.register.container

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rquimbiulco.pokedex.view.auth.register.RegisterUserScreen
import com.rquimbiulco.pokedex.view.auth.register.RegisterUserUiEvent
import com.rquimbiulco.pokedex.view.auth.register.RegisterUserViewModel

@Composable
fun RegisterUserContainer(
    navigateBack: () -> Unit,
    navigateToPokedex: () -> Unit
) {
    val viewModel: RegisterUserViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                RegisterUserUiEvent.NavigateBack -> navigateBack()
                RegisterUserUiEvent.NavigateToPokedex -> navigateToPokedex()
                is RegisterUserUiEvent.ShowError -> snackBarHostState.showSnackbar(event.message)
            }
        }
    }

    RegisterUserScreen(
        state = state,
        onAction = viewModel::onAction,
        snackBarHostState = snackBarHostState,
    )
}