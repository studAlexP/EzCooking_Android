package com.example.ezcooking.repository

import com.example.ezcooking.models.ListMeals
import com.example.ezcooking.models.Recipe
import com.example.ezcooking.network.KtorClient
import com.example.ezcooking.network.RecipeDetailClient
import com.example.ezcooking.utils.Constants
import io.ktor.client.call.*
import io.ktor.client.request.*

object RecipeRepository {

    suspend fun getRecipes(ingredient: String?): ListMeals {
        return KtorClient.client
            .get(Constants.MEAL_URL_INGREDIENTS + "?i=${ingredient}").body()
    }

    suspend fun getRecipesById(id: String): Recipe {
        return RecipeDetailClient.client
            .get(Constants.MEAL_URL_ID + "?i=${id}").body()
    }
}