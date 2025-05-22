package com.example.peekstore.presentation.login.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.peekstore.presentation.login.state.LoginState

class LoginViewModel: ViewModel() {

    private val _loginState: MutableState<LoginState> = mutableStateOf(LoginState())
    val loginState: State<LoginState> get() = _loginState

    fun onUsernameChanged(username: String) {
        _loginState.value = _loginState.value.copy(username = username)
    }
    fun onEmailChanged(email: String) {
        _loginState.value = _loginState.value.copy(email = email)
    }
    fun onPasswordChanged(password: String) {
        _loginState.value = _loginState.value.copy(password = password)
    }
    fun onPasswordVisibilityChanged() {
        _loginState.value = _loginState.value.copy(isPasswordVisible = !loginState.value.isPasswordVisible)
    }

}