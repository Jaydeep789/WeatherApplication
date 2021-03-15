package com.example.myapplication.model

import android.os.Parcelable
import com.example.myapplication.network.CurrentWeather
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Weather(
    val temperature: Double,
    val feels_like: Double,
    val temperature_minimum: Double,
    val temperature_maximum: Double,
    val type: @RawValue List<CurrentWeather>,
    val description: @RawValue List<CurrentWeather>
) : Parcelable
