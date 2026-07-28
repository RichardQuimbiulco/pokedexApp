package com.rquimbiulco.pokedex.view.core.navigation

import androidx.navigation.NavController

fun NavController.navigateToPokedex() {
    navigate(Pokedex) {
        popUpTo(graph.id) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.navigateToLogin() {
    navigate(Login) {
        popUpTo(graph.id) {
            inclusive = true
        }
        launchSingleTop = true
    }
}