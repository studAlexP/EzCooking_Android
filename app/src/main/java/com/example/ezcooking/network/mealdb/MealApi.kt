package com.example.ezcooking.network.mealdb

import com.example.ezcooking.models.ListMeals
import com.example.ezcooking.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.*

fun getKeywordsMeal() {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(Constants.MEAL_URL_INGREDIENTS)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: java.io.IOException) {}

        override fun onResponse(call: Call, response: Response) {
            val body = response.body?.string()
            val gson = GsonBuilder().create()

            val response = gson.fromJson(body, ListMeals::class.java)

        }
    })


}