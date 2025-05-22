package com.example.peekstore.domain.repository

import com.example.peekstore.presentation.login.state.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    suspend fun login(email:String,password:String):AuthResult

    suspend fun register(email:String,password:String):AuthResult

    fun signout()

    val currentUser: StateFlow<FirebaseUser?>
}