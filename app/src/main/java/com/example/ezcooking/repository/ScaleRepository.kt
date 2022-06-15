package com.example.ezcooking.repository

import com.example.ezcooking.models.Keyword
import com.example.ezcooking.network.KtorClient
import io.ktor.client.request.*
import com.example.ezcooking.utils.Constants
import io.ktor.client.call.*

object ScaleRepository {

    suspend fun getKeywords(): Keyword {
        return KtorClient.client
            .get(Constants.SCALE_URL).body()
    }
}