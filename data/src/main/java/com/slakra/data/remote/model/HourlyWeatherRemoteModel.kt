package com.slakra.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Data class to map Server Current Weather data
 * @author sumitlakra
 * @date 06/04/2021
 */

data class HourlyWeatherRemoteModel(
    @SerializedName("dt")
    val timeStamp: Long,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("weather")
    val weatherModelList: List<WeatherModel>
)
