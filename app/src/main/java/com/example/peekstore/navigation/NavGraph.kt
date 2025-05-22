package com.example.peekstore.navigation
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.peekstore.presentation.home.HomeScreen
import com.example.peekstore.presentation.home.viewmodel.HomeViewModel
import com.example.peekstore.presentation.login.LoginScreen
import com.example.peekstore.presentation.login.viewmodel.LoginViewModel


@Composable
fun NavGraph(navController: NavHostController, loginViewModel: LoginViewModel,homeViewModel: HomeViewModel) {


    NavHost(navController= navController,
        startDestination = AppScreen.LoginScreen.route
    ){
        composable(AppScreen.LoginScreen.route){
            LoginScreen(
                loginViewModel,
                onLoginSuccess = { uid ->
                    navController.navigate(AppScreen.HomeScreen.passUid(uid)){
                        popUpTo(AppScreen.LoginScreen.route) { inclusive = true}
                    }
                }
            )
        }
        composable(
            AppScreen.HomeScreen.route,
            arguments = listOf(navArgument("uid"){type = NavType.StringType})
            ){ backStackEntry ->
            val uid = backStackEntry.arguments?.getString("uid") ?: ""

            HomeScreen(
                uid = uid,
                homeViewModel = homeViewModel,
                onLogout = {
                    navController.navigate(AppScreen.LoginScreen.route){
                        popUpTo(AppScreen.HomeScreen.route) { inclusive = true}
                    }
                }
            )
        }

    }
}


