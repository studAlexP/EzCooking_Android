package com.example.ezcooking.screens.favourite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ezcooking.R
import com.example.ezcooking.navigation.RecipeScreens
import com.example.ezcooking.ui.theme.RasberryRed
import com.example.ezcooking.ui.theme.YokeYellow
import com.example.ezcooking.viewmodels.FavouritesViewModel
import com.example.ezcooking.widget.RecipeCards

@Composable
fun FavouriteScreen(
    viewModel: FavouritesViewModel = viewModel(),
    navController: NavController = rememberNavController()
){

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = YokeYellow,
                elevation = 3.dp,

            ){
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "up-button",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(
                        modifier = Modifier
                            .width(20.dp)
                    )

                    Text(text = "My Favourite Recipes", fontSize = 18.sp)
                }
            }
        },
        bottomBar = {
            BottomAppBar(backgroundColor = RasberryRed) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.list),
                        contentDescription = "Recipes",
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable(
                                onClick = { navController.navigate(RecipeScreens.HomeScreen.name) }
                            ),
                        alignment = Alignment.BottomStart
                    )
                    Image(
                        painter = painterResource(id = R.drawable.shopping_cart),
                        contentDescription = "Shopping Cart",
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable(
                                onClick = { navController.navigate(RecipeScreens.ListScreen.name) }
                            ),
                        alignment = Alignment.Center
                    )
                    Image(
                        painter = painterResource(id = R.drawable.loupe),
                        contentDescription = "Search",
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable(
                                onClick = { navController.navigate(RecipeScreens.SearchScreen.name) }
                            ),
                        alignment = Alignment.BottomEnd
                    )
                }

            }
        }

    ){
        FavouriteScreenContent(viewModel = viewModel, navController = navController)
    }

}

@Composable
fun FavouriteScreenContent(
    viewModel: FavouritesViewModel = viewModel(),
    navController: NavController
){
    var favouriteRecipeList = viewModel.getAllRecipe()

    LazyColumn {
        items(favouriteRecipeList){ recipes ->
            RecipeCards(
                recipe = recipes,
                viewFavIconState = true,
                State = viewModel.checkFavourite(recipes),
                onItemClick = { recipeId ->
                    navController.navigate(route = RecipeScreens.DetailScreen.name + "/$recipeId")

                }
            )
        }


    }
}