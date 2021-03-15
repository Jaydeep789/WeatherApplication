package com.example.myapplication.di

import android.provider.SyncStateContract
import com.example.myapplication.network.weatherApi
import com.example.myapplication.utils.Constants

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    fun providesWeatherServiceApi(retrofit: Retrofit) : weatherApi{
        return retrofit.create(weatherApi::class.java)
    }
}