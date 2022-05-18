package com.example.ezcooking.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.example.ezcooking.ui.theme.AndroidGreen
import com.example.ezcooking.ui.theme.EzCookingTheme
import com.example.ezcooking.ui.theme.RasberryRed

@Composable
fun HomeScreen(navController: NavController) {
    EzCookingTheme {
        Scaffold(
            topBar = {
                TopAppBar(backgroundColor = Color.Black) {
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
                }
            },
            bottomBar = {
                BottomAppBar(backgroundColor = RasberryRed) {
                    Row {
                       Image(
                            painter = painterResource(id = R.drawable.list),
                            contentDescription = "Recipes",
                            modifier = Modifier.padding(5.dp),
                            alignment = Alignment.BottomStart
                        )
                        Image(
                            painter = painterResource(id = R.drawable.shopping_cart),
                            contentDescription = "Shopping Cart",
                            modifier = Modifier.padding(5.dp),
                            alignment = Alignment.Center
                        )
                        Image(
                            painter = painterResource(id = R.drawable.loupe),
                            contentDescription = "Search",
                            modifier = Modifier.padding(5.dp),
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