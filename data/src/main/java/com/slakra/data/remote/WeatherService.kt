package com.slakra.data.remote

import com.slakra.data.remote.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service class to hit weather api
 * @author sumitlakra
 * @date 06/04/2021
 */
interface WeatherService {

    @GET("onecall?")
    suspend fun getCurrentAndForecastWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude", encoded = true) exclude: String?,
        @Query("units") units: String?,
        @Query("appid") apiKey: String
    ): Response<WeatherResponse>
}