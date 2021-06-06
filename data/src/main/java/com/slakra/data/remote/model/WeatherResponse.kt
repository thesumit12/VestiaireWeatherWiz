package com.slakra.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Data class to map Server weather response
 * @author sumitlakra
 * @date 06/04/2021
 */
data class WeatherResponse(
    @SerializedName("current")
    val currentWeatherModel: CurrentWeatherRemoteModel,
    @SerializedName("hourly")
    val hourlyWeatherList: List<HourlyWeatherRemoteModel>,
    @SerializedName("daily")
    val dailyWeatherRemoteList: List<DailyWeatherRemoteModel>
)
