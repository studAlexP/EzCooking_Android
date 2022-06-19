package com.example.ezcooking.screens.detail


import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.ezcooking.R
import com.example.ezcooking.models.MealX
import com.example.ezcooking.navigation.RecipeScreens
import com.example.ezcooking.ui.theme.AndroidGreen
import com.example.ezcooking.ui.theme.RasberryRed
import com.example.ezcooking.viewmodels.RecipeDetailViewModel
import com.example.ezcooking.viewmodels.ShoppingListViewModel
import kotlinx.coroutines.delay


@Composable
fun DetailScreen(
    groceryViewModel: ShoppingListViewModel = viewModel(),
    navController: NavController = rememberNavController(),
    recipeId: String?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = RasberryRed,
                elevation = 3.dp,

                ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = "up-button",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(
                        modifier = Modifier
                            .width(20.dp)
                    )

                    Text(
                        text = "Recipe",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
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
            CustomCircularProgressBar(groceryViewModel = groceryViewModel, recipeId = recipeId)
        }
    }
}

@Composable
fun DetailScreenContent(
    groceryViewModel: ShoppingListViewModel,
    recipe: MealX?
) {

    val scrollState = rememberScrollState()
    val ingredients = mutableListOf<String>()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier.verticalScroll(state = scrollState)
        ) {
            if (recipe != null) {
                Text(
                    text = recipe.strMeal,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .padding(0.dp, 10.dp, 0.dp, 20.dp)
                        .align(CenterHorizontally)
                )

                AsyncImage(
                    model = recipe.strMealThumb,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(0.dp, 0.dp, 0.dp, 20.dp)
                        .clip(CircleShape)
                )

                Row {

                    val context = LocalContext.current
                    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(recipe.strYoutube))

                    Image(
                        painter = painterResource(id = R.drawable.youtube),
                        contentDescription = "Recipes",
                        modifier = Modifier
                            .padding(330.dp, 0.dp, 0.dp, 0.dp)
                            .clickable(
                                onClick = { context.startActivity(webIntent) }
                            )
                    )
                }

                Text(
                    text = "Details:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5
                )

                Text(
                    text = "Catogorie: ${recipe.strCategory}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
                )

                Divider(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp))

                Text(
                    text = "Ingredients:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5
                )


                if (recipe.strIngredient1 != "") {
                    ingredients.add(recipe.strIngredient1)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure1,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient1,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                if (recipe.strIngredient2 != "") {
                    ingredients.add(recipe.strIngredient2)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure2,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient2,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                if (recipe.strIngredient3 != "") {
                    ingredients.add(recipe.strIngredient3)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure3,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient3,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                if (recipe.strIngredient4 != "") {
                    ingredients.add(recipe.strIngredient4)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure4,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient4,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                if (recipe.strIngredient5 != "") {
                    ingredients.add(recipe.strIngredient5)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure5,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient5,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                if (recipe.strIngredient6 != "") {
                    ingredients.add(recipe.strIngredient6)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure6,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient6,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                if (recipe.strIngredient7 != "") {
                    ingredients.add(recipe.strIngredient7)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure7,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient7,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                if (recipe.strIngredient8 != "") {
                    ingredients.add(recipe.strIngredient8)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure8,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient8,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                if (recipe.strIngredient9 != "") {
                    ingredients.add(recipe.strIngredient9)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure9,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient9,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                if (recipe.strIngredient10 != "") {
                    ingredients.add(recipe.strIngredient10)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure10,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient10,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                if (recipe.strIngredient11 != "") {
                    ingredients.add(recipe.strIngredient11)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure11,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient11,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                if (recipe.strIngredient12 != "") {
                    ingredients.add(recipe.strIngredient12)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure12,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient12,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                if (recipe.strIngredient13 != "") {
                    ingredients.add(recipe.strIngredient13)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure13,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient13,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                if (recipe.strIngredient14 != "") {
                    ingredients.add(recipe.strIngredient14)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure14,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient14,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                if (recipe.strIngredient15 != "") {
                    ingredients.add(recipe.strIngredient15)
                    Row {
                        Column(modifier = Modifier.width(150.dp)) {
                            Text(
                                text = recipe.strMeasure15,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            )
                        }
                        Column(modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(
                                text = recipe.strIngredient15,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                ShoppingListIcon(ingredients, groceryViewModel)

                Divider(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp))

                Text(
                    text = "Instructions:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5
                )

                Text(
                    text = recipe.strInstructions,
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 20.dp)
                )
            }
        }
    }
}

@Composable
private fun CustomCircularProgressBar(
    groceryViewModel: ShoppingListViewModel,
    recipeId: String?
) {
    var dataLoaded by remember { mutableStateOf(false) }

    if (recipeId != null) {
        RecipeDetailViewModel.id = recipeId
    }

    val recipeViewModel: RecipeDetailViewModel = viewModel()
    val recipeData = recipeViewModel.detail.collectAsState()
    var recipe: MealX? = null

    recipeData.value?.let {
        Log.d("TEST12", it.meals.toString())
        recipe = it.meals[0]
    }

    LaunchedEffect(Unit) {
        recipeViewModel.getRecipeDetails()
        delay(2000)
        dataLoaded = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (!dataLoaded) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center),
                color = AndroidGreen,
                strokeWidth = 10.dp,
            )
        }
    }

    recipeData.value?.let {
        recipe = it.meals[0]
    }


    if (dataLoaded) {
        DetailScreenContent(groceryViewModel = groceryViewModel, recipe = recipe)
    }
}

@Composable
fun ShoppingListIcon(
    ingredients: List<String>,
    groceryViewModel: ShoppingListViewModel

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 10.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End
    ) {

        Image(
            painter = painterResource(id = R.drawable.addbutton),
            contentDescription = "Add to shopping list",
            modifier = Modifier
                .clickable(
                    onClick = {
                        groceryViewModel.addMultipleIngredients(ingredients)
                    }
                )
        )
    }

}