package com.rquimbiulco.pokedex.view.auth

import android.util.Patterns
import javax.inject.Inject

class Validator @Inject constructor() {

    fun isValidEmail(email: String): Boolean =
        !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun validatePasswordMatch(password: String, confirmPassword: String): Boolean =
        (password != confirmPassword)

    fun isValidPassword(password: String): Boolean {
        // Requires: 8+ chars, 1 Uppercase, 1 Lowercase, 1 Digit, 1 Special Char
        val passwordRegex =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$".toRegex()
        return !password.matches(passwordRegex)
    }

    fun isFormValid(email: String, password: String, confirmPassword: String): Boolean {
        return !isValidEmail(email) && !isValidPassword(password) && !validatePasswordMatch(
            password,
            confirmPassword
        )
    }
}