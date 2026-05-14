package com.rquimbiulco.pokedex.domain.usecase

import com.rquimbiulco.pokedex.domain.model.UserModel
import com.rquimbiulco.pokedex.domain.repository.AuthRepository
import com.rquimbiulco.pokedex.domain.model.LoginResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: String, password: String): LoginResult {
        return authRepository.login(user, password)
    }
}