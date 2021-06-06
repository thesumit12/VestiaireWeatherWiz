package com.slakra.domain.repository

import com.slakra.common.ResultState
import com.slakra.domain.entity.CurrentWeatherEntity
import kotlinx.coroutines.flow.Flow

/**
 * Interface to provide current weather business logic
 * @author sumitlakra
 * @date 06/04/2021
 */
interface CurrentWeatherRepo {

    fun getCurrentWeather(): Flow<ResultState<CurrentWeatherEntity>>
}