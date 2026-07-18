package com.example.weatherapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.data.remote.api.dto.Hour
import com.example.weatherapp.helper.formatHour

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherHourItem(hour: Hour) {

    Card(
        modifier = Modifier
            .width(90.dp)
            .padding(end = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.12f),
            contentColor = Color.White
        ),

        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.25f)),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val formattedHour = formatHour(hour.time)
            Text(formattedHour)
            Spacer(Modifier.height(10.dp))
            AsyncImage(
                model = "https:${hour.condition.icon}",
                contentDescription = "Weather Icon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .size(50.dp)
            )
            Spacer(Modifier.height(10.dp))
            Text("${hour.temp_c.toInt()}°")
        }
    }


}
