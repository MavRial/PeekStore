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
            val userUidNullable by TokenManager.getUserUid(context).collectAsState(initial = null)
            val userUid = userUidNullable ?: ""

            LaunchedEffect(userUid) {
                if (!userUid.isNotEmpty()) {
                    navController.navigate(AppScreen.HomeScreen.passUid(userUid)) {

                        popUpTo(AppScreen.LoginScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                } else {

                    navController.navigate(AppScreen.LoginScreen.route) {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                }
            }
            NavGraph(navController = navController, loginViewModel = loginViewModel , homeViewModel = homeViewModel)
        }
    }
}

