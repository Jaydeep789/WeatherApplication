package com.example.myapplication.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Weather
import com.example.myapplication.repo.WeatherRepository
import com.example.myapplication.utils.DataState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class WeatherViewmodel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherData: MutableLiveData<DataState<List<Weather>>> = MutableLiveData()
    val weatherData: LiveData<DataState<List<Weather>>> = _weatherData

    fun getWeatherByCity(search: String) = viewModelScope.launch {
        weatherRepository.getWeatherForecast(search).collect {
            _weatherData.value = it
        }
    }
}