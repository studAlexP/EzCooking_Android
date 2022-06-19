package com.example.ezcooking.viewmodels


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.ezcooking.models.Meal


class ShoppingListViewModel : ViewModel() {
    private var ingredients = mutableStateListOf<String>()

    fun removeIngredients(ingredientList: List<String>){
        ingredients.removeAll(ingredientList)
    }

    fun getAllIngredients(): List<String>{
        return ingredients
    }

    fun addMultipleIngredients(ingredientList: List<String>) {
        ingredients.addAll(ingredientList)
    }

    fun checkIngredientList(ingredient: String): Boolean{
        return ingredients.contains(ingredient)
    }
}