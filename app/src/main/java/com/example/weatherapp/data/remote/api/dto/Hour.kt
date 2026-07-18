package com.example.weatherapp.data.remote.api.dto

data class Hour(
    val time: String,
    val temp_c: Double,
    val wind_kph: Double,
    val humidity: Int,
    val condition: Condition

)
