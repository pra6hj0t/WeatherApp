package com.example.weatherapp.data.remote.api.dto

data class Current(
    val feelslike_c: Double,
    val wind_kph: Double,
    val humidity: Int,
    val last_updated: String,
    val temp_c: Double,
    val temp_f: Double,
    val is_day: Int,
    val condition: Condition


)
