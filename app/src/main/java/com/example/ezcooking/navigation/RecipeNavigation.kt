package com.example.ezcooking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ezcooking.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RecipeScreens.HomeScreen.name
    ) {
        composable(RecipeScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
    }
}