package com.example.peekstore.presentation.login.state

sealed class AuthResult {
    object Idle : AuthResult()
    data object Loading : AuthResult()
    data class Success(val uid: String) : AuthResult()
    data class Error(val message: String) : AuthResult()
}