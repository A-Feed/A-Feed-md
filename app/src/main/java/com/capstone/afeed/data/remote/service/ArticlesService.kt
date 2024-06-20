package com.capstone.afeed.data.remote.service

import com.capstone.afeed.data.remote.response.GetArticlesResponse
import retrofit2.http.GET

interface ArticlesService {
    @GET("everything?q=aquaculture")
    suspend fun getArticles() : GetArticlesResponse
}