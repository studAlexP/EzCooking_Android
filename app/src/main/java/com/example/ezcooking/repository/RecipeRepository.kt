package com.example.ezcooking.repository

import com.example.ezcooking.models.ListMeals
import com.example.ezcooking.models.Recipe
import com.example.ezcooking.network.KtorClient
import com.example.ezcooking.utils.Constants
import io.ktor.client.request.*

object RecipeRepository {

    suspend fun getRecipes(ingredient: String): ListMeals {
        return KtorClient.httpClient.use {
            it.get(Constants.MEAL_URL_INGREDIENTS + "?i=${ingredient}")
        }
    }

    suspend fun getRecipesById(id: String): Recipe {
        return KtorClient.httpClient.use {
            it.get(Constants.MEAL_URL_ID + "?i=${id}")
        }
    }
}