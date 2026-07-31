package com.rquimbiulco.pokedex.view.auth.login.container

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rquimbiulco.pokedex.R
import com.rquimbiulco.pokedex.view.auth.login.LoginError
import com.rquimbiulco.pokedex.view.auth.login.LoginEvent
import com.rquimbiulco.pokedex.view.auth.login.LoginScreen
import com.rquimbiulco.pokedex.view.auth.login.LoginViewModel

@Composable
fun LoginContainer(
    navigateToRegister: () -> Unit,
    navigatePokedexScreen: () -> Unit
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                LoginEvent.NavigateToRegister -> navigateToRegister()
                LoginEvent.NavigateToPokedex -> navigatePokedexScreen()
                is LoginEvent.ShowError -> snackBarHostState.showSnackbar(
                    event.error.asString(
                        context
                    )
                )
            }
        }
    }

    LoginScreen(
        state = state,
        onAction = viewModel::onAction,
        snackBarHostState = snackBarHostState,
    )
}

fun LoginError.asString(context: Context): String =
    when (this) {
        LoginError.InvalidCredentials ->
            context.getString(R.string.invalid_credentials)

        else ->
            context.getString(R.string.unknown_error)
    }