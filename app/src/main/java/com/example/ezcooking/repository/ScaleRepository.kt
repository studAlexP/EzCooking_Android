package com.example.ezcooking.repository

import com.example.ezcooking.models.Keyword
import com.example.ezcooking.network.KtorClient
import io.ktor.client.request.*
import com.example.ezcooking.utils.Constants

object ScaleRepository {

    suspend fun getKeywords(): Keyword {
        return KtorClient.httpClient.use {
            it.get(Constants.SCALE_URL)
        }
    }
}