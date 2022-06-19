package com.example.ezcooking.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
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
import com.example.ezcooking.viewmodels.ShoppingListViewModel

@Composable
fun ListScreen(
    groceryViewModel: ShoppingListViewModel,
    navController: NavController = rememberNavController()
) {

    val ingredients = groceryViewModel.getAllIngredients()

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

    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            ListScreenContent(ingredients = ingredients, groceryViewModel = groceryViewModel)
        }
    }

}

@Composable
fun ListScreenContent(
    ingredients: List<String>,
    groceryViewModel: ShoppingListViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "delete",
            modifier = Modifier
                .padding(10.dp)
                .clickable(onClick = {
                    groceryViewModel.removeIngredients(ingredients)
                })
        )
    }
    LazyColumn(
        modifier = Modifier.width(350.dp)
    ) {
        items(ingredients) {
            Text(
                text = it,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(20.dp, 5.dp, 0.dp, 0.dp)
            )
        }
    }
}