package com.slakra.domain.repository

import com.slakra.common.ResultState

/**
 * Interface to provide server weather data business logic
 * @author sumitlakra
 * @date 06/05/2021
 */
interface WeatherRepo {

    suspend fun getWeatherDetails(request: GetWeatherRequest): com.slakra.common.ResultState<Boolean>
}