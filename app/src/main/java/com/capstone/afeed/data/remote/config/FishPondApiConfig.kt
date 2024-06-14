package com.capstone.afeed.data.remote.config

import com.capstone.afeed.BuildConfig
import com.capstone.afeed.data.remote.service.FishPondService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// this is config for data that have corelation with FishPond And Iot System and user need to give the authorization token
class FishPondApiConfig {
    companion object {
        fun getService(token: String): FishPondService {

            val client: OkHttpClient

            val loggingInterceptor = if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            ) else HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.NONE
            )

            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", token)
                    .build()
                chain.proceed(requestHeaders)
            }

            client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build()


            val retrofit = Retrofit.Builder()
                .baseUrl("https://7106-2001-448a-2018-2b19-a83e-52ce-1412-2b9d.ngrok-free.app/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(FishPondService::class.java)

        }
    }
}