package com.example.giphyapp.repository

import com.example.giphyapp.giphyapi.NetworkClient
import com.example.giphyapp.dataModel.GifModel
import com.example.giphyapp.constans.Constants.Companion.GIPFY_API_KEY
import retrofit2.Response

class Repository {
    suspend fun searshGifs(query: String): Response<GifModel> {
        return NetworkClient.api.getGifs(GIPFY_API_KEY, 30, query)
    }

    suspend fun trendGifs(): Response<GifModel> {
        return NetworkClient.api.getTrendGifs(GIPFY_API_KEY, 30)
    }
}
