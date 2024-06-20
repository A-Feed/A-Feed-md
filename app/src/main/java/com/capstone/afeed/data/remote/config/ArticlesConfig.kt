package com.capstone.afeed.data.remote.config

import com.capstone.afeed.BuildConfig
import com.capstone.afeed.data.remote.service.ArticlesService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// this for articles that get from news api
class ArticlesConfig {
    companion object {
        fun getService(): ArticlesService {
            val client: OkHttpClient

            val loggingInterceptor = if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            ) else HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.NONE
            )

            val apiKey = BuildConfig.API_KEY_NEWS

            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("X-Api-Key", apiKey)
                    .build()
                chain.proceed(requestHeaders)
            }

            client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()


            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ArticlesService::class.java)

        }
    }
}