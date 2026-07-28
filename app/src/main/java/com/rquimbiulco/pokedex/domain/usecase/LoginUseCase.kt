package com.rquimbiulco.pokedex.domain.usecase

import com.rquimbiulco.pokedex.domain.model.LoginResult
import com.rquimbiulco.pokedex.domain.repository.AuthRepository
import com.rquimbiulco.pokedex.domain.repository.SessionRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(user: String, password: String): LoginResult {
        val result = authRepository.login(user, password)
        if (result is LoginResult.Success) {
            sessionRepository.login()
        }
        return result
    }
}