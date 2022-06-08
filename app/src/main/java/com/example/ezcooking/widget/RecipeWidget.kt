package com.example.ezcooking.widget

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ezcooking.models.Meal import com.example.ezcooking.models.MealX
import com.example.ezcooking.testRecipe.Recipe
import com.example.ezcooking.testRecipe.getRecipes
import com.example.ezcooking.viewmodels.RecipeDetailViewModel
import com.example.ezcooking.viewmodels.RecipeViewModel

@Composable
fun RecipeCards(
    meal: Meal = GetRecipes("Chicken")[0],
    viewFavIconState: Boolean,
    State: Boolean,
    onItemClick: (String) -> Unit = {},
    onFavouriteClick: (Meal) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(meal.idMeal)
            }
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(100.dp)) {
                AsyncImage(
                    model = meal.strMealThumb,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)

            ) {
                FavouriteIcon(
                    meal,
                    viewFavouriteIcon = viewFavIconState,
                    State = State
                ) { meal ->
                    onFavouriteClick(meal)
                }

                Text(
                    text = meal.strMeal,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )

                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp
                )

                /*Text(
                    text = "Categorie: ${recipe.categorie}",
                    style = MaterialTheme.typography.caption
                )*/


            }
        }
    }
}


@Composable
fun FavouriteIcon(
    meal: Meal,
    viewFavouriteIcon: Boolean,
    State: Boolean,
    onFavouriteClick: (Meal) -> Unit = {}
) {
    var favouriteChecked by remember { mutableStateOf(State) }

    if (viewFavouriteIcon) {

        if (!favouriteChecked) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "favourite-border",
                    modifier = Modifier
                        .clickable(onClick = {
                            favouriteChecked = !favouriteChecked
                            onFavouriteClick(meal)
                        })
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favourite",
                    modifier = Modifier
                        .clickable(onClick = {
                            favouriteChecked = !favouriteChecked
                            onFavouriteClick(meal)
                        })
                )
            }
        }

    }
}
/*
@Composable
fun RecipeDetails(meal: Meal = GetRecipes()[0]){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Text(
            text = "Ingredients: ${meal.ingredients}",
            fontSize = MaterialTheme.typography.body1.fontSize,
            overflow = TextOverflow.Ellipsis
        )
        Divider(
            color = Color.Black,
            thickness = 1.dp
        )
        Text(
            text = "Steps: ${meal.steps}",
            fontSize = MaterialTheme.typography.body1.fontSize,
            overflow = TextOverflow.Ellipsis
        )

    }
}
*/

@Composable
fun GetRecipes(ingredient: String): List<Meal> {
    RecipeViewModel.ingredient = ingredient
    val viewModel: RecipeViewModel = viewModel()
    val data = viewModel.recipes.collectAsState()
    var meals = listOf<Meal>()

    data.value?.let {
        meals = it.meals
    }

    return meals
}

@Composable
fun GetRecipesById(id: String): MealX? {
    RecipeDetailViewModel.id = id
    val viewModel: RecipeDetailViewModel = viewModel()
    val data = viewModel.detail.collectAsState()
    var meal: MealX? = null

    data.value?.let {
        meal = it.meals[0]
    }

    return meal
}