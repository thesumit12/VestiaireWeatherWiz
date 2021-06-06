package com.slakra.domain.entity

/**
 * Data class to hold hourly weather data
 * @author sumitlakra
 * @date 06/04/2021
 */
data class HourlyWeatherEntity(val timestamp: Long, val temp: String, val weatherEntity: WeatherEntity)
