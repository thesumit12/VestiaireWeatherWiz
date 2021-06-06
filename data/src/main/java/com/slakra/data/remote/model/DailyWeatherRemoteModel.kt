package com.slakra.data.remote.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Data class to map Server Daily Weather data
 * @author sumitlakra
 * @date 06/04/2021
 */

data class DailyWeatherRemoteModel(
    @SerializedName("dt")
    val timeStamp: Long,
    @SerializedName("weather")
    val weatherModelList: List<WeatherModel>,
    @SerializedName("temp")
    val temperatureModel: TemperatureRemoteModel,
    @SerializedName("pressure")
    val pressure: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("uvi")
    val uvIndex: String,
    @SerializedName("wind_speed")
    val wind_speed: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
