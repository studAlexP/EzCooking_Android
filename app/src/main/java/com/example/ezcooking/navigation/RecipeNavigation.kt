package com.example.ezcooking.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ezcooking.screens.detail.DetailScreen
import com.example.ezcooking.screens.favourite.FavouriteScreen

import com.example.ezcooking.screens.home.HomeScreen
import com.example.ezcooking.screens.list.ListScreen
import com.example.ezcooking.screens.search.SearchScreen
import com.example.ezcooking.viewmodels.FavouritesViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val favouritesViewModel: FavouritesViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = RecipeScreens.HomeScreen.name
    ) {
        composable(RecipeScreens.HomeScreen.name) {
            HomeScreen(
                viewModel = favouritesViewModel,
                navController = navController
            )
        }

        composable(
            RecipeScreens.FavouriteScreen.name) {
            FavouriteScreen(
                viewModel = favouritesViewModel,
                navController = navController
            )
        }

        composable(
            RecipeScreens.DetailScreen.name) {
            DetailScreen(
                viewModel = favouritesViewModel,
                navController = navController
            )
        }

        composable(
            RecipeScreens.ListScreen.name) {
            ListScreen(navController = navController)
        }

        composable(
            RecipeScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

    }
}