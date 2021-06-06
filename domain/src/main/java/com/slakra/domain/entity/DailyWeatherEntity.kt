package com.slakra.domain.entity

/**
 * Data class to hold daily weather data
 * @author sumitlakra
 * @date 06/04/2021
 */
data class DailyWeatherEntity(
    val timeStamp: Long, val weatherCode: Int,
    val minTemp: String, val maxTemp: String,
    val pressure: String, val humidity: String, val windSpeed: String,
    val uvIndex: String
)
