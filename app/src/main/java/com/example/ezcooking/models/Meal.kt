package com.example.ezcooking.models

import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)