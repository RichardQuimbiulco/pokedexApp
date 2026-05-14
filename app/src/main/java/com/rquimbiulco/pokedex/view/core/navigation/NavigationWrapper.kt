package com.rquimbiulco.pokedex.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rquimbiulco.pokedex.view.auth.login.LoginScreen
import com.rquimbiulco.pokedex.view.auth.register.RegisterScreen
import com.rquimbiulco.pokedex.view.pokedex.PokedexScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Pokedex) {
        composable<Login> {
            LoginScreen(
                navigateToRegister = { navController.navigate(Register) },
                navigatePokedexScreen = { navController.navigate(Pokedex) })
        }
        composable<Register> {
            RegisterScreen(
                navigateBack = { navController.popBackStack() },
                navigatePokedexScreen = { navController.navigate(Pokedex) })
        }
        composable<Pokedex> {
            PokedexScreen()
        }
    }
}