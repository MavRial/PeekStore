package com.example.peekstore.navigation

sealed class AppScreen(val route: String) {
    data object LoginScreen : AppScreen("login")
    data object HomeScreen : AppScreen("home")
}