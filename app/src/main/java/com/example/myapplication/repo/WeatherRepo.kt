package com.example.myapplication.repo

import com.example.myapplication.BuildConfig
import com.example.myapplication.model.Weather
import com.example.myapplication.network.WeatherForecastContainer
import com.example.myapplication.network.asDomainModel
import com.example.myapplication.network.weatherApi
import com.example.myapplication.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    private val weatherApi: weatherApi
) {
    suspend fun getWeatherForecast(city: String): Flow<DataState<List<Weather>>> = flow {
        emit(DataState.Loading)

        try {
            val result: WeatherForecastContainer = weatherApi.getForecast(city,BuildConfig.API_KEY)
            val domainResult: List<Weather> = result.asDomainModel()
            emit(DataState.Success(domainResult))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}