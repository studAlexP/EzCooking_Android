package com.example.ezcooking.repository

import com.example.ezcooking.models.ListMeals
import com.example.ezcooking.models.Recipe
import com.example.ezcooking.network.RecipeKtorClient
import com.example.ezcooking.network.ScaleKtorClient
import com.example.ezcooking.utils.Constants
import io.ktor.client.request.*

object RecipeRepository {

    suspend fun getRecipes(ingredient: String?): ListMeals {
        return RecipeKtorClient.httpClient.use {
            it.get(Constants.MEAL_URL_INGREDIENTS + "?i=${ingredient}")
        }
    }

    suspend fun getRecipesById(id: String): Recipe {
        return RecipeKtorClient.httpClient.use {
            it.get(Constants.MEAL_URL_ID + "?i=${id}")
        }
    }
}