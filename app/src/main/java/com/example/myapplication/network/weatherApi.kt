package com.example.myapplication.network

import retrofit2.http.GET
import retrofit2.http.Query

interface weatherApi {

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city : String,
        @Query("appid") key : String
    ) : WeatherForecastContainer
}