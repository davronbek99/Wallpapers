package com.example.a4kfullwalpapers.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.pexels.com/v1/"

    const val API_KEY = "563492ad6f917000010000012171b6f7a46543ccb4b32b2c70e94f45"
//    const val API_KEY = "563492ad6f917000010000016e1b0769c4294c6e955c935ecfb10e6e"
//    const val API_KEY = "563492ad6f917000010000012a1bcfb5afd94b2697261120a06faca6"


    fun getRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}