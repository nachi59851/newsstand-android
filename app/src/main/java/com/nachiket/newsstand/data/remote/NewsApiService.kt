package com.nachiket.newsstand.data.remote


import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("1/latest")
    suspend fun getLatestNews(
        @Query("apikey") apikey: String,
        @Query("country") country: String = "in",
        @Query("language") language: String = "en"
    ): NewsResponseDto
}

