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
import com.example.ezcooking.models.Meal
import com.example.ezcooking.testRecipe.Recipe
import com.example.ezcooking.testRecipe.getRecipes
import com.example.ezcooking.viewmodels.RecipeViewModel

@Composable
fun RecipeCards(
    recipe: Recipe = getRecipes()[0],
    viewFavIconState: Boolean,
    State: Boolean,
    onItemClick: (String) -> Unit = {},
    onFavouriteClick: (Recipe) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(recipe.id)
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
                    model = recipe.images[0],
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
                    recipe,
                    viewFavouriteIcon = viewFavIconState,
                    State = State
                ) { recipe ->
                    onFavouriteClick(recipe)
                }

                Text(
                    text = recipe.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5
                )

                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp
                )

                Text(
                    text = "Categorie: ${recipe.categorie}",
                    style = MaterialTheme.typography.caption
                )


            }
        }
    }
}


@Composable
fun FavouriteIcon(
    recipe: Recipe,
    viewFavouriteIcon: Boolean,
    State: Boolean,
    onFavouriteClick: (Recipe) -> Unit = {}
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
                            onFavouriteClick(recipe)
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
                            onFavouriteClick(recipe)
                        })
                )
            }
        }

    }
}

@Composable
fun RecipeDetails(recipe: Recipe = getRecipes()[0]){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)){
        Text(
            text = "Ingredients: ${recipe.ingredients}",
            fontSize = MaterialTheme.typography.body1.fontSize,
            overflow = TextOverflow.Ellipsis
        )
        Divider(
            color = Color.Black,
            thickness = 1.dp
        )
        Text(
            text = "Steps: ${recipe.steps}",
            fontSize = MaterialTheme.typography.body1.fontSize,
            overflow = TextOverflow.Ellipsis
        )

    }
}

@Composable
fun GetRecipes() : List<Meal> {
    val viewModel: RecipeViewModel = viewModel()
    val data = viewModel.recipes.collectAsState()
    var recipes = listOf<Meal>()

    data.value?.let {
        recipes = it.meals
    }

    return recipes
}