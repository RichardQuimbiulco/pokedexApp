package com.rquimbiulco.pokedex.view.details

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenEvent

interface DetailEvent: ScreenEvent {
    data object NavigateBack : DetailEvent
}