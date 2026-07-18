package com.example.weatherapp.data.remote.repository

import com.example.weatherapp.data.remote.api.WeatherApi
import com.example.weatherapp.data.remote.api.dto.WeatherResponse
import com.example.weatherapp.util.Constants.API_KEY
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {


    suspend fun getWeatherData(city: String): WeatherResponse {
        return weatherApi.getWeather(city = city, API_KEY)
    }
}