package com.example.ezcooking.models

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val meals: List<MealX>
)