package com.rquimbiulco.pokedex.domain.security

interface PasswordEncryptor {
    fun encrypt(password: String): String
    fun decrypt(encryptedPassword: String): String
}