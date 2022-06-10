package com.example.ezcooking.widget

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ezcooking.models.Meal

@Composable
fun RecipeCards(
    meal: Meal,
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