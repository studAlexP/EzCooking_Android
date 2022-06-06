package com.example.ezcooking.repository

import com.example.ezcooking.models.ListMeals
import com.example.ezcooking.network.KtorClient
import com.example.ezcooking.utils.Constants
import io.ktor.client.request.*

object RecipeRepository {

    suspend fun getRecipes(): ListMeals {
        return KtorClient.httpClient.use {
            it.get(Constants.MEAL_URL_INGREDIENTS)
        }
    }
}