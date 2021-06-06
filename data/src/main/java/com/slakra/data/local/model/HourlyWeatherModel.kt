package com.slakra.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.slakra.data.remote.model.WeatherModel

/**
 * Hourly weather Room Entity
 * @author sumitlakra
 * @date 06/04/2021
 */
@Entity(tableName = "hourly_weather")
data class HourlyWeatherModel(
    val timeStamp: Long,
    val temp: String,
    @Embedded
    val weather: WeatherModel,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
