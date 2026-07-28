package com.rquimbiulco.pokedex.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rquimbiulco.pokedex.view.auth.login.LoginScreen
import com.rquimbiulco.pokedex.view.auth.register.container.RegisterUserContainer
import com.rquimbiulco.pokedex.view.auth.splash.SplashScreen
import com.rquimbiulco.pokedex.view.auth.splash.SplashViewModel
import com.rquimbiulco.pokedex.view.pokedex.PokeDexViewModel
import com.rquimbiulco.pokedex.view.pokedex.PokedexScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Splash) {
        composable<Login> {
            LoginScreen(
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
            val pokeDexViewModel: PokeDexViewModel = hiltViewModel()
            PokedexScreen(
                navigateToLogin = {
                    navController.navigateToLogin()
                },
                viewmodel = pokeDexViewModel
            )
        }
        composable<Splash> {
            val splashViewModel: SplashViewModel = hiltViewModel()
            SplashScreen(
                navigateToLogin = {
                    navController.navigateToLogin()
                },
                navigateToPokedex = {
                    navController.navigateToPokedex()
                },
                viewmodel = splashViewModel
            )
        }
    }

}