package com.example.ezcooking.screens.list

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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ezcooking.R
import com.example.ezcooking.navigation.RecipeScreens
import com.example.ezcooking.ui.theme.CartPink
import com.example.ezcooking.ui.theme.RasberryRed

@Composable
fun ListScreen(navController: NavController = rememberNavController()){
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

                    Text(text = "Shopping List", fontSize = 18.sp)
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
        ListScreenContent()
    }

}

@Composable
fun ListScreenContent() {

}