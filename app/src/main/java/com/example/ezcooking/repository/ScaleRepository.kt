package com.example.ezcooking.repository

import com.example.ezcooking.models.Keyword
import com.example.ezcooking.network.ScaleKtorClient
import io.ktor.client.request.*
import com.example.ezcooking.utils.Constants

object ScaleRepository {

    suspend fun getKeywords(): Keyword {
        return ScaleKtorClient.httpClient.use {
            it.get(Constants.SCALE_URL)
        }
    }
}