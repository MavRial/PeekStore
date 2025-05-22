package com.example.peekstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.peekstore.data.service.FirebaseModule
import com.example.peekstore.navigation.AppScreen
import com.example.peekstore.navigation.NavGraph
import com.example.peekstore.presentation.home.viewmodel.HomeViewModel
import com.example.peekstore.presentation.login.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

import kotlinx.coroutines.awaitCancellation


class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            var startDestination by remember { mutableStateOf<String?>(null) }


            LaunchedEffect(Unit) {
                val listener = FirebaseAuth.AuthStateListener { auth ->
                    val user = auth.currentUser
                    startDestination = if (user != null) {
                        AppScreen.HomeScreen.route
                    } else {
                        AppScreen.LoginScreen.route
                    }
                }
                FirebaseModule.auth.addAuthStateListener(listener)

                awaitCancellation()
            }

            startDestination?.let {
                NavGraph(
                    navController = navController,
                    loginViewModel = loginViewModel,
                    homeViewModel = homeViewModel,
                    startDestination = it
                )
            }
        }
    }
}

