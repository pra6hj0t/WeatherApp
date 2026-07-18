package com.example.weatherapp.data.remote.api

import com.example.weatherapp.data.remote.api.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast.json")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("key") apiKey: String,
        @Query("days") days: Int = 1
    ): WeatherResponse
}