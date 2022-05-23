package com.example.ezcooking.network.scale

import com.example.ezcooking.models.Keyword
import com.example.ezcooking.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.*


fun getKeywords() {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(Constants.SCALE_URL + "data")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: java.io.IOException) {}

        override fun onResponse(call: Call, response: Response) {
            val body = response.body?.string()
            val gson = GsonBuilder().create()

            val response = gson.fromJson(body, Keyword::class.java)
        }
    })

}
