package com.example.giphyapp.giphyapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.giphyapp.constans.Constants.Companion.BASE_URL

object NetworkClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: GiphyApi by lazy {
        retrofit.create(GiphyApi::class.java)
    }
}