package com.example.ezcooking.backbone.okhttp

import com.example.ezcooking.BuildConfig
import okhttp3.*


fun getRecipes() {
    val client = OkHttpClient()
    val apiKey = BuildConfig.EDAMAMA_API_KEY
    val appId = BuildConfig.EDAMAMA_APP_ID

    val request = Request.Builder()
        .url("https://api.edamam.com/api/recipes/v2" +
                "?type=public" +
                "&q=chicken" +
                "&app_id=$appId" +
                "&app_key=$apiKey")
        .header("Content-Type", "application/json")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: java.io.IOException) {}

        override fun onResponse(call: Call, response: Response) {
            println(response.body?.string())
        }
    })

}