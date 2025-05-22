package com.example.peekstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.peekstore.presentation.home.HomeScreen
import com.example.peekstore.presentation.login.LoginScreen
import com.example.peekstore.presentation.login.viewmodel.LoginViewModel


class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
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

