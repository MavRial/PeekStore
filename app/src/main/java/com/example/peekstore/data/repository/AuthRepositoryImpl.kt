package com.example.peekstore.data.repository

import com.example.peekstore.data.service.FirebaseModule
import com.example.peekstore.domain.repository.AuthRepository
import com.example.peekstore.presentation.login.state.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl: AuthRepository {

    private val auth = FirebaseModule.auth



    private val _currentUser = MutableStateFlow(auth.currentUser)
    override val currentUser: StateFlow<FirebaseUser?> = _currentUser


    private val authListener = FirebaseAuth.AuthStateListener {
        _currentUser.value = it.currentUser
    }

    init {
        auth.addAuthStateListener(authListener)
    }

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

    override fun signout() {
        auth.signOut()
    }
}