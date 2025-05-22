package com.example.peekstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.peekstore.data.service.TokenManager
import com.example.peekstore.presentation.home.HomeScreen
import com.example.peekstore.presentation.login.LoginScreen
import com.example.peekstore.presentation.login.viewmodel.LoginViewModel



class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            val userUid by TokenManager.getUserUid(context).collectAsState(initial = "")

            LaunchedEffect(userUid) {
                if (!userUid.isNullOrEmpty()) {
                    navController.navigate("home/${userUid}") {
                        popUpTo(0) // elimina todo el backstack
                    }
                }
            }

                    LoginScreen(
                        loginViewModel = loginViewModel,
                        loginState = loginViewModel.loginState.value,
                        onLoginSuccess = { uid ->
                            navController.navigate("home/$uid"){
                                popUpTo("login"){ inclusive = true}
                            }
                        }
                    )
        }
    }
}

