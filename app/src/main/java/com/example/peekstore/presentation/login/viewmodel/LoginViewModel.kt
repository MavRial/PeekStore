package com.example.peekstore.presentation.login.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peekstore.data.repository.AuthRepositoryImpl
import com.example.peekstore.domain.repository.AuthRepository
import com.example.peekstore.presentation.login.state.AuthResult
import com.example.peekstore.presentation.login.state.LoginState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository = AuthRepositoryImpl()
): ViewModel() {

    private val _authResult = mutableStateOf<AuthResult>(AuthResult.Idle)
    val authResult: State<AuthResult> get() = _authResult

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> get() = _loginState

    val currentUser = authRepository.currentUser.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

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


    fun login(){
        viewModelScope.launch {
             _authResult.value = AuthResult.Loading

            val result = authRepository.login(
                loginState.value.email,
                loginState.value.password
            )

            _authResult.value = result
        }
    }

    fun register(){
        viewModelScope.launch {
            _authResult.value = AuthResult.Loading

            val result = authRepository.register(
                loginState.value.email,
                loginState.value.password
            )

            _authResult.value = result
        }
    }
}