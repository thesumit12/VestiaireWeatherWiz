package com.slakra.domain.usecase

import com.slakra.common.ResultState
import com.slakra.domain.entity.CurrentWeatherEntity
import com.slakra.domain.repository.CurrentWeatherRepo
import kotlinx.coroutines.flow.Flow

/**
 * UseCase to get current weather details
 * @author sumitlakra
 * @date 06/04/2021
 */
class GetCurrentWeatherUseCase(
    private val currentWeatherRepo: CurrentWeatherRepo
) {

    fun execute(): Flow<ResultState<CurrentWeatherEntity>> {
        return currentWeatherRepo.getCurrentWeather()
    }
}