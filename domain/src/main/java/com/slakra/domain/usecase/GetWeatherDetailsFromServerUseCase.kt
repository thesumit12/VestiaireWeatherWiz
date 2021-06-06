package com.slakra.domain.usecase

import com.slakra.domain.arch.BaseNetworkWithParamUseCase
import com.slakra.common.ResultState
import com.slakra.domain.repository.GetWeatherRequest
import com.slakra.domain.repository.WeatherRepo

/**
 * UseCase to get weather details from server
 * @author sumitlakra
 * @date 06/04/2021
 */
class GetWeatherDetailsFromServerUseCase(private val weatherRepo: WeatherRepo):
    BaseNetworkWithParamUseCase<GetWeatherRequest, Boolean> {

    override suspend fun execute(param: GetWeatherRequest): com.slakra.common.ResultState<Boolean> {
        return weatherRepo.getWeatherDetails(param)
    }
}