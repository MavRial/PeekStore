package com.example.peekstore.navigation
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController

import androidx.navigation.compose.composable

import com.example.peekstore.presentation.home.HomeScreen
import com.example.peekstore.presentation.home.viewmodel.HomeViewModel
import com.example.peekstore.presentation.login.LoginScreen
import com.example.peekstore.presentation.login.viewmodel.LoginViewModel



@Composable
fun NavGraph(
     navController: NavHostController,
     loginViewModel: LoginViewModel,
     homeViewModel: HomeViewModel,
     startDestination: String
) {
     NavHost(
          navController = navController,
          startDestination = startDestination
     ) {

          composable(AppScreen.LoginScreen.route){
               LoginScreen(
                    loginViewModel = loginViewModel,
                    onLoginSuccess = {
                         navController.navigate(AppScreen.HomeScreen.route) {
                              popUpTo(navController.graph.startDestinationId) { inclusive = true }
                         }
                    }
               )
          }

          composable(AppScreen.HomeScreen.route){
               HomeScreen(
                    homeViewModel = homeViewModel,
                    onLogout = {
                         navController.navigate(AppScreen.LoginScreen.route) {
                              popUpTo(navController.graph.startDestinationId) { inclusive = true }
                         }
                    }
               )
          }
     }
}


