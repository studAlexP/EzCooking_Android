package com.example.ezcooking.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ezcooking.screens.detail.DetailScreen
import com.example.ezcooking.screens.favourite.FavouriteScreen

import com.example.ezcooking.screens.home.HomeScreen
import com.example.ezcooking.screens.list.ListScreen
import com.example.ezcooking.screens.search.SearchScreen
import com.example.ezcooking.viewmodels.FavouritesViewModel
import com.example.ezcooking.viewmodels.ShoppingListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val favouritesViewModel: FavouritesViewModel = viewModel()
    val groceryViewModel: ShoppingListViewModel = viewModel()

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
            RecipeScreens.DetailScreen.name + "/{recipe}",
            arguments = listOf(navArgument("recipe") {
            type = NavType.StringType
            })
        ){
            backStackEntry ->
            DetailScreen(
                groceryViewModel = groceryViewModel,
                navController = navController,
                backStackEntry.arguments?.getString("recipe")
            )
        }

        composable(
            RecipeScreens.ListScreen.name) {
            ListScreen(groceryViewModel,navController = navController)
        }

        composable(
            RecipeScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

    }
}