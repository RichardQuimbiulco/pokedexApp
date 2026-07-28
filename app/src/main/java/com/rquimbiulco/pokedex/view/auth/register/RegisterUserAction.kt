package com.rquimbiulco.pokedex.view.auth.register

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenAction

interface RegisterAction : ScreenAction {
    data object OnSaveButtonClicked : RegisterAction
}