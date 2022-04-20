package com.example.ezcooking.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.ezcooking.ui.theme.EzCookingTheme

@Composable
fun HomeScreen(navController: NavController) {
    EzCookingTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = "EzCooking",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                })
            }
        ) {
            HomeScreenContent()
        }
    }
}

@Composable
fun HomeScreenContent() {
    Text(text = "TEst")
}