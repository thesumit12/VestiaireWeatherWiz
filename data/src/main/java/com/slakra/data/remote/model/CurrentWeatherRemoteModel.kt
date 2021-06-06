package com.slakra.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Data class to map Server Current Weather data
 * @author sumitlakra
 * @date 06/04/2021
 */
data class CurrentWeatherRemoteModel(

    @SerializedName("dt")
    val timeStamp: Long,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feels_like: Double,
    @SerializedName("pressure")
    val pressure: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("uvi")
    val uvIndex: String,
    @SerializedName("wind_speed")
    val wind_speed: Double,
    @SerializedName("weather")
    val weatherModelList: List<WeatherModel>,
    @SerializedName("visibility")
    val visibility: String,
    @SerializedName("dew_point")
    val dew_point: Double
)
