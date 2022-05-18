package com.example.ezcooking.backbone.okhttp

import okhttp3.*
import okio.IOException


fun getEdamam() {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://api.edamam.com/api/recipes/v2" +
                "?type=public" +
                "&q=chicken" +
                "&app_id=" +
                "&app_key=")
        .header("Content-Type", "application/json")
        .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: java.io.IOException) {

        }

        override fun onResponse(call: Call, response: Response) = println(response.body?.string())
    })

}