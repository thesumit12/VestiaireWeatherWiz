package com.slakra.domain.repository

data class GetWeatherRequest(
    val lat: String,
    val lon: String,
    val exclude: String?,
    val units: String?
)
