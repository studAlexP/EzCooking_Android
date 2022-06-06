package com.example.ezcooking.models

import kotlinx.serialization.Serializable

@Serializable
data class ListMeals(
    val meals: List<Meal>
)

