package com.yaman.belediyehizmet.feature.weather

import com.yaman.belediyehizmet.R

data class WeatherItem(
    val hour : String,
    val icon :Int,
    val degree : String
)

val weatherList = listOf(
    WeatherItem(
        "12:00",
        icon = R.drawable.wind,
        "10°C"
    ),
    WeatherItem(
        "14:00",
        icon = R.drawable.wind,
        "10°C"
    ),
    WeatherItem(
        "16:00",
        icon = R.drawable.wind,
        "10°C"
    ),WeatherItem(
        "20:00",
        icon = R.drawable.wind,
        "10°C"
    ),
    WeatherItem(
        "22:00",
        icon = R.drawable.wind,
        "10°C"
    )
)
