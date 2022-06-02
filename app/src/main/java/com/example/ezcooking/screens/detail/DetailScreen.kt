package com.example.ezcooking.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ezcooking.R
import com.example.ezcooking.navigation.RecipeScreens
import com.example.ezcooking.testRecipe.Recipe
import com.example.ezcooking.testRecipe.getRecipes
import com.example.ezcooking.ui.theme.CartPink
import com.example.ezcooking.ui.theme.RasberryRed
import com.example.ezcooking.viewmodels.FavouritesViewModel
import com.example.ezcooking.widget.RecipeCards
import com.example.ezcooking.widget.RecipeDetails


@Composable
fun DetailScreen(
    viewModel: FavouritesViewModel = viewModel(),
    navController: NavController = rememberNavController(),
    recipeId: String? = "ChickenAvocado"
) {
    val recipe = filterRecipe(recipeId = recipeId)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = CartPink,
                elevation = 3.dp,

                ) {
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

                    Text(text = recipe.title)
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

    ) {
        DetailScreenContent(viewModel = viewModel, recipe = recipe)
    }

}

@Composable
fun DetailScreenContent(
    viewModel: FavouritesViewModel = viewModel(),
    recipe: Recipe
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column {
            RecipeCards(
                recipe = recipe,
                viewFavIconState = true,
                State = viewModel.checkFavourite(recipe),
                onFavouriteClick = {
                    if (viewModel.checkFavourite(it)) {
                        viewModel.removeRecipe(it)
                    } else {
                        viewModel.addRecipe(it)
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            RecipeDetails()
        }
    }
}

fun filterRecipe(recipeId: String?): Recipe {
    return getRecipes().filter { recipe -> recipe.id == recipeId }[0]
}