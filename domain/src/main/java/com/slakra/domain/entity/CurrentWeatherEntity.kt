package com.slakra.domain.entity

/**
 * Data class to hold current weather data
 * @author sumitlakra
 * @date 06/04/2021
 */
data class CurrentWeatherEntity(
    val timeStamp: Long, val sunrise: Long, val sunset: Long,
    val temp: String, val feelsLike: String,
    val pressure: String, val humidity: String,
    val uvIndex: String, val windSpeed: String,
    val weatherEntity: WeatherEntity, val visibility: String,
    val dewPoint: String
)
