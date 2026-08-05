package com.rquimbiulco.pokedex.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rquimbiulco.pokedex.view.auth.login.container.LoginContainer
import com.rquimbiulco.pokedex.view.auth.register.container.RegisterUserContainer
import com.rquimbiulco.pokedex.view.auth.splash.container.SplashContainer
import com.rquimbiulco.pokedex.view.details.container.DetailContainer
import com.rquimbiulco.pokedex.view.pokedex.container.PokedexContainer

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Splash) {
        composable<Login> {
            LoginContainer(
                navigateToRegister = { navController.navigate(Register) },
                navigatePokedexScreen = { navController.navigateToPokedex() })
        }
        composable<Register> {
            RegisterUserContainer(
                navigateBack = { navController.popBackStack() },
                navigateToPokedex = { navController.navigateToPokedex() }
            )
        }
        composable<Pokedex> {
            PokedexContainer(
                navigateToLogin = {
                    navController.navigateToLogin()
                }
            )
        }
        composable<Splash> {
            SplashContainer(
                navigateToLogin = {
                    navController.navigateToLogin()
                },
                navigateToPokedex = {
                    navController.navigateToPokedex()
                }
            )
        }
        composable<Detail> {
            DetailContainer(
                navigateBack = { navController.popBackStack() }
            )
        }
    }

}