package com.example.myapplication.model

import com.example.myapplication.network.CurrentWeather

data class Weather(
    val temperature: Double,
    val feels_like: Double,
    val temperature_minimum: Double,
    val temperature_maximum: Double,
    val type: List<CurrentWeather>,
    val description: List<CurrentWeather>
)
