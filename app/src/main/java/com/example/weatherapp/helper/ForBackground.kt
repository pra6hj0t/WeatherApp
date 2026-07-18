package com.example.weatherapp.helper

import com.example.weatherapp.R


fun getBackgroundForCondition(condition: String?): Int{
    val backgroundColor = when {
        condition?.contains("sunny") == true || condition?.contains("clear") == true ->
            R.drawable.sunny

        condition?.contains("cloud") == true || condition?.contains("overcast") == true ->
            R.drawable.cloudy

        condition?.contains("rain") == true || condition?.contains("drizzle") == true ->
            R.drawable.rainy

        condition?.contains("thunder") == true ->
            R.drawable.storm

        condition?.contains("snow") == true || condition?.contains("blizzard") == true ->
            R.drawable.snow

        condition?.contains("mist") == true || condition?.contains("fog") == true ->
            R.drawable.fog

        else -> R.drawable.default_image
    }
    return backgroundColor

}