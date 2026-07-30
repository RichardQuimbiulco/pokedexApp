package com.rquimbiulco.pokedex.view.auth.login

interface LoginError {
    data object InvalidCredentials : LoginError
    data object Unknown : LoginError
}