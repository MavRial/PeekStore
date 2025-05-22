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
import androidx.navigation.compose.rememberNavController
import com.example.peekstore.data.service.TokenManager
import com.example.peekstore.navigation.AppScreen
import com.example.peekstore.navigation.NavGraph
import com.example.peekstore.presentation.home.viewmodel.HomeViewModel
import com.example.peekstore.presentation.login.viewmodel.LoginViewModel



class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            val userUid by TokenManager.getUserUid(context).collectAsState(initial = "")

            LaunchedEffect(userUid) {
                if (!userUid.isNullOrEmpty()) {
                    userUid?.let { uid ->
                        navController.navigate(AppScreen.HomeScreen.passUid(uid)){
                            popUpTo(0)
                        }
                    }
                }
            }
            NavGraph(navController = navController, loginViewModel = loginViewModel , homeViewModel = homeViewModel)
        }
    }
}

