package com.example.weatherapp.presentation

import com.example.weatherapp.data.remote.api.dto.WeatherResponse

sealed interface UiStates {

    object Idle : UiStates

    object Loading : UiStates

    data class Success(val data: WeatherResponse) : UiStates

    data class Error(val message: String) : UiStates


}