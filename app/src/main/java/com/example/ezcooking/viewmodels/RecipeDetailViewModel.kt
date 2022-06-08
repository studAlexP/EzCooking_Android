package com.example.ezcooking.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezcooking.models.Recipe
import com.example.ezcooking.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecipeDetailViewModel: ViewModel() {
    var detail = MutableStateFlow<Recipe?>(null)

    companion object {
        lateinit var id: String
    }

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                RecipeRepository.getRecipesById(id)
            }.onSuccess {
                detail.value = it
            }.onFailure {
                detail.value = null
            }
        }
    }
}