package com.example.ezcooking.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezcooking.models.ListMeals
import com.example.ezcooking.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    val recipes = MutableStateFlow<ListMeals?>(null)

    companion object {
        lateinit var ingredient: String
    }

    fun getRecipes() {
        viewModelScope.launch {
            kotlin.runCatching {
                RecipeRepository.getRecipes(ingredient)
            }.onSuccess {
                recipes.value = it
            }.onFailure {
                recipes.value = null
            }
        }
    }
}