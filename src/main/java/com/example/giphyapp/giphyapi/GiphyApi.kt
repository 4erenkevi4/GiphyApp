package com.example.giphyapp.giphyapi

import com.example.giphyapp.dataModel.GifModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("search")
    suspend fun getGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("q") category: String
    ): Response<GifModel>

    @GET("trending")
    suspend fun getTrendGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int
    ): Response<GifModel>
}