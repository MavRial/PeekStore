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
            val uid = result.user?.uid
            if (uid.isNullOrEmpty()){
                AuthResult.Error("No se pudo obtener el UiD del usuario")
            }else {
                AuthResult.Success(uid)
            }

        }catch (e:Exception){
            AuthResult.Error(e.message ?: "Error desconocido durante el login")
        }
    }

    override suspend fun register(email:String, password:String):AuthResult
    {
        return try {
            val result = auth.createUserWithEmailAndPassword(email,password).await()
            val uid = result.user?.uid
            if (uid.isNullOrEmpty()){
                AuthResult.Error("No se pudo obtener el UID del usuario.")
            } else {
        AuthResult.Success(uid)
    }
        }catch (e:Exception){
            AuthResult.Error(e.localizedMessage ?: "Error desconocido durante el registro.")
        }
    }
}