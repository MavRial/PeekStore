package com.example.peekstore.data.repository

import com.example.peekstore.data.service.FirebaseModule
import com.example.peekstore.domain.repository.AuthRepository
import com.example.peekstore.presentation.login.state.AuthResult
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl: AuthRepository {

    private val auth = FirebaseModule.auth

    override suspend fun login(email:String, password:String):AuthResult
    {
        return try {
            val result = auth.signInWithEmailAndPassword(email,password).await()
            val uid = result.user?.uid ?: return AuthResult.Error("no se pudo obtener el UID")
            AuthResult.Success(uid)
        }catch (e:Exception){
            AuthResult.Error(e.message ?: "Error desconocido")
        }
    }
}