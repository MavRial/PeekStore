package com.example.peekstore.presentation.login.viewmodel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peekstore.data.repository.AuthRepositoryImpl
import com.example.peekstore.data.service.TokenManager
import com.example.peekstore.domain.repository.AuthRepository
import com.example.peekstore.presentation.login.state.AuthResult
import com.example.peekstore.presentation.login.state.LoginState
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository = AuthRepositoryImpl()
): ViewModel() {

    private val _authResult = mutableStateOf<AuthResult>(AuthResult.Idle)
    val authResult: State<AuthResult> get() = _authResult

    private val _loginState = mutableStateOf(LoginState())
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


    fun login(context: Context, onLoginSucces: (String) -> Unit){
        viewModelScope.launch {
             _authResult.value = AuthResult.Loading

            val result = authRepository.login(
                loginState.value.email,
                loginState.value.password
            )

            when (result){
                is AuthResult.Success -> {
                    // guardamos el uid en data store
                    TokenManager.saveUserUid(context,result.uid)

                    _authResult.value = result

                    onLoginSucces(result.uid)
                }
                is AuthResult.Error -> {
                    _authResult.value = result
                }

                else -> {}
            }
        }
    }

}