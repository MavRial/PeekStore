package com.example.peekstore.navigation
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.peekstore.presentation.login.LoginScreen
import com.example.peekstore.presentation.login.viewmodel.LoginViewModel


@Composable
fun NavGraph(navController: NavHostController, loginViewModel: LoginViewModel) {


    NavHost(navController= navController,
        startDestination = AppScreen.LoginScreen.route
    ){
        composable(AppScreen.LoginScreen.route){
            LoginScreen(loginViewModel, navController)
        }
        composable(AppScreen.HomeScreen.route){
            //HomeScreen(navController)
        }

    }
}


