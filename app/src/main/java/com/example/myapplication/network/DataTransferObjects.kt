package com.example.myapplication.network

import com.example.myapplication.model.Weather
import com.google.gson.annotations.SerializedName

data class CurrentMainWeather(
    @SerializedName("temp")
    val temperature : Double,
    @SerializedName("feels_like")
    val feels_like : Double,
    @SerializedName("temp_min")
    val temperature_min : Double,
    @SerializedName("temp_max")
    val temperature_max : Double
)

data class CurrentWeather(
    @SerializedName("main")
    val weather_type : String,
    @SerializedName("description")
    val description : String
)

data class WeatherForecast(
    @SerializedName("main")
    val main : CurrentMainWeather,
    @SerializedName("weather")
    val weather: List<CurrentWeather>
)

data class WeatherForecastContainer(
    @SerializedName("list")
    val weatherList : List<WeatherForecast>
)

/**
 * Extension function to convert network result into domain object
 */
fun WeatherForecastContainer.asDomainModel() : List<Weather>{
    return weatherList.map { weatherForecast ->
        Weather(
            temperature = weatherForecast.main.temperature,
            feels_like = weatherForecast.main.feels_like,
            temperature_minimum = weatherForecast.main.temperature_min,
            temperature_maximum = weatherForecast.main.temperature_max,
            type = weatherForecast.weather,
            description = weatherForecast.weather
        )
    }
}