package com.slakra.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Data class to map Sever Weather data
 * @author sumitlakra
 * @date 06/04/2021
 */
data class WeatherModel(
    @SerializedName("id")
    val code: Int,

    @SerializedName("main")
    val main: String,

    @SerializedName("description")
    val description: String
)
