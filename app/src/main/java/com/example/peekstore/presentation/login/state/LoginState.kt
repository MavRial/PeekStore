package com.example.peekstore.presentation.login.state

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

data class LoginState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val passwordVisualTransformation: VisualTransformation = PasswordVisualTransformation(),


)
