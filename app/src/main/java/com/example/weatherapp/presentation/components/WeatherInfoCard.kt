package com.example.weatherapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.data.remote.api.dto.WeatherResponse

@Composable
fun WeatherInfoCard(weather: WeatherResponse) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.15f),
            contentColor = Color.Black
        ),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.3f))
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

            ) {

                AsyncImage(
                    model = "https:${weather.current.condition.icon}",
                    contentDescription = "Weather Icon",
                    modifier = Modifier
                        .size(100.dp),

                )
                Text(
                    "${weather.current.feelslike_c.toInt()}%",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,

                    )
                Spacer(Modifier.height(5.dp))

                Text(
                    weather.current.condition.text,
                    color = Color.White,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }



            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .padding(top = 25.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {

                Image(
                    painter = painterResource(R.drawable.humidity),
                    contentDescription = "Humidity Icon",
                    modifier = Modifier
                        .size(60.dp)
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    "${weather.current.humidity}%",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(5.dp))
                Text("Humidity", color = Color.White, fontSize = 18.sp)
            }

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .padding(top = 25.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(R.drawable.wind),
                    contentDescription = "Wind Icon",
                    modifier = Modifier
                        .size(60.dp)
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    "${weather.current.wind_kph} Km/h",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(5.dp))
                Text("Wind Speed", color = Color.White, fontSize = 18.sp)
            }
        }


    }

}