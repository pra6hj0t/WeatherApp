package com.example.weatherapp.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.data.remote.api.dto.WeatherResponse
import com.example.weatherapp.helper.getBackgroundForCondition
import com.example.weatherapp.presentation.UiStates
import com.example.weatherapp.presentation.components.WeatherHourItem
import com.example.weatherapp.presentation.components.WeatherInfoCard
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {

    var currentWeather by remember {
        mutableStateOf<WeatherResponse?>(null)
    }

    val weather = (viewModel.uiState as? UiStates.Success)?.data

    val condition = currentWeather?.current?.condition?.text?.lowercase()

    val scrollState = rememberScrollState()


    val backgroundColor = getBackgroundForCondition(condition)


    var city by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


    Crossfade(targetState = backgroundColor,
        animationSpec = tween(durationMillis = 2000)) { backgroundColor ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(backgroundColor),
                    contentScale = ContentScale.Crop
                )
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                containerColor = Color.Transparent
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        placeholder = { Text("Search any city ....") },
                        singleLine = true,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White.copy(alpha = 0.15f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.15f),

                            focusedIndicatorColor = Color.Black.copy(alpha = 0.3f),
                            unfocusedIndicatorColor = Color.Black.copy(alpha = 0.3f),
                            cursorColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black,

                            ),
                        shape = RoundedCornerShape(20.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.Black
                            )

                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                if (city.isNotBlank()) {
                                    viewModel.getWeatherData(city = city.trim())
                                }
                                city = ""
                                keyboardController?.hide()
                                focusManager.clearFocus()
                            }
                        )
                    )








                    when (val state = viewModel.uiState) {
                        is UiStates.Idle -> {

                            Spacer(Modifier.height(100.dp))
                            Text(
                                text = "Please enter a city !",
                                color = Color.White
                            )


                        }

                        is UiStates.Loading -> {
                            Spacer(Modifier.height(100.dp))
                            CircularProgressIndicator()
                            Text(
                                text = "Fetching Weather...",
                                color = Color.White
                            )

                        }

                        is UiStates.Success -> {

                            currentWeather = state.data

                            AsyncImage(
                                model = "https:${weather?.current?.condition?.icon}",
                                contentDescription = "Weather Icon",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .size(180.dp),


                                )

                            Spacer(Modifier.height(10.dp))


                            Text(
                                "${weather?.location?.name}, ${weather?.location?.country}",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 26.sp,
                                color = Color.White

                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                "${weather?.current?.temp_c?.toInt()}°C",
                                fontSize = 72.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(Modifier.height(10.dp))

                            Text(
                                "${weather?.current?.condition?.text}",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White
                            )
                            Spacer(Modifier.height(32.dp))


                            WeatherInfoCard(state.data)

                            Spacer(Modifier.height(28.dp))

                            Text(
                                "Today",
                                fontSize = 22.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(16.dp))

                            LazyRow(modifier = Modifier.fillMaxWidth()) {

                                items(
                                    weather?.forecast?.forecastday?.get(0)?.hour?.size ?: 0
                                ) { index ->
                                    WeatherHourItem(
                                        weather?.forecast?.forecastday?.get(0)?.hour?.get(
                                            index
                                        )!!
                                    )
                                }

                            }

                        }

                        is UiStates.Error -> {
                            Spacer(Modifier.height(100.dp))
                            Text(text = "Error: ${state.message}")
                        }
                    }

                }

            }


        }
    }

}




