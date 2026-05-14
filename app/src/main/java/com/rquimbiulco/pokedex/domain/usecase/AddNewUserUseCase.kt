package com.rquimbiulco.pokedex.domain.usecase

import com.rquimbiulco.pokedex.domain.model.UserRegisterModel
import com.rquimbiulco.pokedex.domain.repository.UserRepository
import javax.inject.Inject

class AddNewUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(userModel: UserRegisterModel) = userRepository.insertUser(userModel)
}