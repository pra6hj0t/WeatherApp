package com.example.weatherapp.helper

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
fun formatHour(time: String) : String{

    // 1. Parse the input string into a LocalDateTime object
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH)
    val dateTime = LocalDateTime.parse(time, inputFormatter)

    // 2. Format it to output only the 12-hour format ("12 AM")
    val outputFormatter = DateTimeFormatter.ofPattern("h a", Locale.ENGLISH)
    val formattedHour = dateTime.format(outputFormatter)

    return formattedHour
}