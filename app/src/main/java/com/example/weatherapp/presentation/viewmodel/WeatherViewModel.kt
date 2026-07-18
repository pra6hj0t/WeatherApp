package com.example.weatherapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.remote.repository.WeatherRepository
import com.example.weatherapp.presentation.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    var uiState by mutableStateOf<UiStates>(UiStates.Idle)


    fun getWeatherData(city: String) {
        viewModelScope.launch {

            uiState = UiStates.Loading
            try {
                val data = weatherRepository.getWeatherData(city)
                uiState = UiStates.Success(data)
            } catch (e: Exception) {
                uiState = UiStates.Error(e.message ?: "Unknown error")
            }

        }
    }
}