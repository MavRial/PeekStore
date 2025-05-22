package com.example.peekstore.navigation

sealed class AppScreen(val route:String) {
    object LoginScreen:AppScreen("loginScreen")
    object HomeScreen:AppScreen("homeScreen")
}