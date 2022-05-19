package com.example.ezcooking.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ezcooking.R
import com.example.ezcooking.navigation.RecipeScreens
import com.example.ezcooking.ui.theme.AndroidGreen
import com.example.ezcooking.ui.theme.MintGreen
import com.example.ezcooking.ui.theme.EzCookingTheme
import com.example.ezcooking.ui.theme.RasberryRed

@Composable
fun HomeScreen(navController: NavController) {

    var showMenu by remember {
        mutableStateOf(false)
    }

    EzCookingTheme {
        Scaffold(
            topBar = {
                TopAppBar(backgroundColor = MintGreen, title = {
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
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                        }

                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(onClick = { navController.navigate(RecipeScreens.FavouriteScreen.name) }) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favourites",
                                        modifier = Modifier.padding(4.dp)
                                    )
                                    Text(
                                        text = "Favourites",
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
            HomeScreenContent()
        }
    }
}

@Composable
fun HomeScreenContent() {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .height(100.dp),
        elevation = 6.dp
    ) {
        Text(text = "Future Recipes will show up here")
    }
}