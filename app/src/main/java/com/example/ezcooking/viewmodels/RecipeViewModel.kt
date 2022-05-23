package com.example.ezcooking.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.ezcooking.testRecipe.Recipe

class RecipeViewModel : ViewModel() {
    private var recipes = mutableStateListOf<Recipe>()

    fun addRecipe(recipe: Recipe){
        recipes.add(recipe)
    }

    fun removeRecipe(recipe: Recipe){
        recipes.remove(recipe)
    }

    fun getAllRecipe(): List<Recipe>{
        return recipes
    }
    fun checkFavourite(recipe: Recipe): Boolean{
        return recipes.contains(recipe)
    }
}