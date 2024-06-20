package com.capstone.afeed.repository

import androidx.lifecycle.liveData
import com.capstone.afeed.data.ResponseState
import com.capstone.afeed.data.remote.response.ErrorArticlesResponse
import com.capstone.afeed.data.remote.response.ErrorResponse
import com.capstone.afeed.data.remote.service.ArticlesService
import com.google.gson.Gson
import retrofit2.HttpException

class ArticlesRepository(
    private val articlesService: ArticlesService
) {
    suspend fun getNews() = liveData {
        emit(ResponseState.Loading)
        try {
            val result = articlesService.getArticles()
            emit(ResponseState.Success(result))
        } catch (e: HttpException) {
            try {
                val errorMessage = Gson().fromJson<ErrorArticlesResponse>(
                    e.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                emit(ResponseState.Error(errorMessage.message))
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error("Unknown Error"))
        }
    }


}