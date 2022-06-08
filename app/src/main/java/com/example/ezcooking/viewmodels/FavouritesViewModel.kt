package com.example.ezcooking.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.ezcooking.models.Meal

class FavouritesViewModel : ViewModel() {
    private var meals = mutableStateListOf<Meal>()

    fun addRecipe(meal: Meal){
        meals.add(meal)
    }

    fun removeRecipe(meal: Meal){
        meals.remove(meal)
    }

    fun getAllRecipe(): List<Meal>{
        return meals
    }
    fun checkFavourite(meal: Meal): Boolean{
        return meals.contains(meal)
    }
}