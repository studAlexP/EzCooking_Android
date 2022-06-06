package com.example.ezcooking.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ezcooking.viewmodels.ScaleViewModel

@Composable
fun GetKeywords(): List<String> {
    val viewModel: ScaleViewModel = viewModel()
    val data = viewModel.keywords.collectAsState()
    var keywords = listOf<String>()

    data.value?.let {
        keywords = it.keywords
    }

    return keywords
}