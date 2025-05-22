package com.example.peekstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.peekstore.presentation.login.LoginScreen
import com.example.peekstore.presentation.login.viewmodel.LoginViewModel


class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            LoginScreen( loginViewModel = loginViewModel, loginState = loginViewModel.loginState.value)
        }
    }
}

