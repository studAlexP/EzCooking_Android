package com.example.ezcooking.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ezcooking.R
import com.example.ezcooking.models.Meal
import com.example.ezcooking.navigation.RecipeScreens
import com.example.ezcooking.ui.theme.AndroidGreen
import com.example.ezcooking.ui.theme.EzCookingTheme
import com.example.ezcooking.ui.theme.RasberryRed
import com.example.ezcooking.viewmodels.FavouritesViewModel
import com.example.ezcooking.viewmodels.RecipeViewModel
import com.example.ezcooking.viewmodels.ScaleViewModel
import com.example.ezcooking.widget.RecipeCards
import kotlinx.coroutines.*

@Composable
fun HomeScreen(
    viewModel: FavouritesViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {

    var showMenu by remember {
        mutableStateOf(false)
    }

    EzCookingTheme {
        Scaffold(
            topBar = {
                TopAppBar(backgroundColor = Color.Black, title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.padding(5.dp),
                        alignment = Alignment.BottomStart
                    )

                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = AndroidGreen)) {
                                append("Ez")
                            }
                            withStyle(style = SpanStyle(color = RasberryRed)) {
                                append("Cooking")
                            }
                        }
                    )
                },
                    actions = {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More",
                                tint = Color.White
                            )
                        }

                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(onClick = { navController.navigate(RecipeScreens.FavouriteScreen.name) }) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favourite",
                                        modifier = Modifier.padding(4.dp)
                                    )
                                    Text(
                                        text = "Favourite",
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .width(100.dp)
                                    )
                                }

                            }
                        }

                    }
                )
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
                            modifier = Modifier.padding(5.dp),
                            alignment = Alignment.BottomStart,

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
            CustomCircularProgressBar(viewModel, navController)
        }
    }
}


@Composable
private fun CustomCircularProgressBar(
    viewModel: FavouritesViewModel = viewModel(),
    navController: NavController
) {
    var dataLoaded by remember { mutableStateOf(false) }

    val keywordsViewModel: ScaleViewModel = viewModel()
    val keywordsData = keywordsViewModel.keywords.collectAsState()
    var keywords: List<String> = emptyList()

    var meals: List<Meal> = emptyList()

    keywordsData.value?.let {
        keywords = it.keywords
    }


    LaunchedEffect(Unit) {
        delay(2000)
        dataLoaded = true
    }

    Box {
        if (!dataLoaded) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                color = Color.Green,
                strokeWidth = 10.dp
            )
        }
        if (dataLoaded) {
            RecipeViewModel.ingredient = keywords.joinToString(",")
            val viewModel2: RecipeViewModel = viewModel()
            val data = viewModel2.recipes.collectAsState()
            data.value?.let {
                meals = it.meals
            }
            HomeScreenContent(
                viewModel = viewModel,
                navController = navController,
                mealList = meals
            )
        }
    }
}

@Composable
fun HomeScreenContent(
    viewModel: FavouritesViewModel = viewModel(),
    navController: NavController,
    mealList: List<Meal>
) {
    LazyColumn {
        items(mealList) { meals ->
            RecipeCards(
                meal = meals,
                viewFavIconState = true,
                State = viewModel.checkFavourite(meals),
                onFavouriteClick = {
                    if (viewModel.checkFavourite(it)) {
                        viewModel.removeRecipe(it)
                    } else {
                        viewModel.addRecipe(it)
                    }
                },
                onItemClick = { recipeId ->
                    navController.navigate(route = RecipeScreens.DetailScreen.name + "/$recipeId")

                }
            )
        }
    }
}
