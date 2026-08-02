package com.rquimbiulco.pokedex.view.auth.splash.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.rquimbiulco.pokedex.view.auth.splash.SplashScreen
import com.rquimbiulco.pokedex.view.auth.splash.SplashUiEvent
import com.rquimbiulco.pokedex.view.auth.splash.SplashViewModel

@Composable
fun SplashContainer(
    navigateToLogin: () -> Unit,
    navigateToPokedex: () -> Unit,
) {
    val viewModel: SplashViewModel = hiltViewModel()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                SplashUiEvent.NavigateToLogin ->
                    navigateToLogin()

                SplashUiEvent.NavigateToPokedex ->
                    navigateToPokedex()
            }
        }
    }

    SplashScreen()
}