package com.slakra.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.slakra.data.remote.model.WeatherModel

/**
 * Current weather Room Entity
 * @author sumitlakra
 * @date 06/04/2021
 */
@Entity(tableName = "current_weather")
data class CurrentWeatherModel(
    val timeStamp: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: String,
    val feels_like: String,
    val pressure: String,
    val humidity: String,
    val uvIndex: String,
    val wind_speed: String,
    @Embedded
    val weather: WeatherModel,
    val visibility: String,
    val dew_point: String,
    @PrimaryKey
    val id: Int = 1
)