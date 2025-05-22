package com.example.peekstore.navigation

sealed class AppScreen(val route:String) {
    data object LoginScreen:AppScreen("loginScreen")
    data object HomeScreen : AppScreen("homeScreen/{uid}") {
        fun passUid(uid: String) = "homeScreen/$uid"
    }
}