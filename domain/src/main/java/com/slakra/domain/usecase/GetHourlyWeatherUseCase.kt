package com.slakra.domain.usecase

import com.slakra.domain.arch.BaseLocalUseCaseWithParam
import com.slakra.domain.entity.HourlyWeatherEntity
import com.slakra.domain.repository.HourlyWeatherRepo
import kotlinx.coroutines.flow.Flow

/**
 * UseCase to get hourly weather details
 * @author sumitlakra
 * @date 06/04/2021
 */
class GetHourlyWeatherUseCase(
    private val hourlyWeatherRepo: HourlyWeatherRepo
) : BaseLocalUseCaseWithParam<Long,List<HourlyWeatherEntity>> {

    override fun execute(param: Long): Flow<List<HourlyWeatherEntity>> {
        return hourlyWeatherRepo.getHourlyWeatherDetails(param)
    }
}