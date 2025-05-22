package com.example.peekstore.navigation
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.peekstore.presentation.home.HomeScreen
import com.example.peekstore.presentation.home.viewmodel.HomeViewModel
import com.example.peekstore.presentation.login.LoginScreen
import com.example.peekstore.presentation.login.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.firstOrNull


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


