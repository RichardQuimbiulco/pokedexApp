package com.rquimbiulco.pokedex.view.details.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rquimbiulco.pokedex.view.details.DetailEvent
import com.rquimbiulco.pokedex.view.details.DetailScreen
import com.rquimbiulco.pokedex.view.details.DetailViewModel

@Composable
fun DetailContainer(navigateBack: () -> Unit) {
    val viewModel: DetailViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                DetailEvent.NavigateBack -> navigateBack()
            }
        }
    }

    DetailScreen(
        state = state,
        onAction = viewModel::onAction
    )
}