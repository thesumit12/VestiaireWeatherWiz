package com.slakra.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.slakra.data.remote.model.TemperatureRemoteModel
import com.slakra.data.remote.model.WeatherModel

/**
 * Daily Weather Room Entity
 * @author sumitlakra
 * @date 06/04/2021
 */
@Entity(tableName = "daily_weather")
data class DailyWeatherModel(
    val timeStamp: Long,
    @Embedded
    val weather: WeatherModel,
    @Embedded
    val temperature_model: TemperatureRemoteModel,
    val pressure: String,
    val humidity: String,
    val uvIndex: String,
    val wind_speed: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
