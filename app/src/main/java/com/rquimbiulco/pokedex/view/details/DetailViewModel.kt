package com.rquimbiulco.pokedex.view.details

import com.rquimbiulco.pokedex.view.core.architecture.base.BaseViewModel

class DetailViewModel : BaseViewModel<DetailUiState, DetailAction, DetailEvent>(DetailUiState()) {

    override fun handleAction(action: DetailAction) {
        when (action) {
            is DetailAction.OnFavoriteClicked -> {}
            DetailAction.OnBackButtonClicked -> {
                launch { sendEvent(DetailEvent.NavigateBack) }
            }
        }
    }
}