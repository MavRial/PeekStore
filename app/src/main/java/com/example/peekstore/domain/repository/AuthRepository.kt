package com.example.peekstore.domain.repository

import com.example.peekstore.presentation.login.state.AuthResult

interface AuthRepository {
    suspend fun login(email:String,password:String):AuthResult
}