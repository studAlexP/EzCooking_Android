package com.example.ezcooking.models

import kotlinx.serialization.Serializable

@Serializable
data class Keyword(
    val keywords: List<String>
)