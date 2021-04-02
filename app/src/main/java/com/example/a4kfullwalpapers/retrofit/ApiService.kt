package com.example.a4kfullwalpapers.retrofit

import com.example.a4kfullwalpapers.models.ImageData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    fun getImages(
        @Header("Authorization") auth: String = ApiClient.API_KEY,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Call<ImageData>

    @GET("popular")
    fun getPopularImages(
        @Header("Authorization") auth: String = ApiClient.API_KEY,
        @Query("page") page: Int
    ): Call<ImageData>

    @GET("curated")
    fun getRandomImages(
        @Header("Authorization") auth: String = ApiClient.API_KEY,
        @Query("page") page: Int
    ): Call<ImageData>

}