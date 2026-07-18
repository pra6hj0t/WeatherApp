package com.example.weatherapp.data.remote.api.dto

data class WeatherResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast

)
