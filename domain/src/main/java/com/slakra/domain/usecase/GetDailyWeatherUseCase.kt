package com.slakra.domain.usecase

import com.slakra.domain.arch.BaseLocalUseCaseWithParam
import com.slakra.domain.entity.DailyWeatherEntity
import com.slakra.domain.repository.DailyWeatherRepo
import kotlinx.coroutines.flow.Flow

/**
 * UseCase to get daily weather details
 * @author sumitlakra
 * @date 06/04/2021
 */
class GetDailyWeatherUseCase(
    private val dailyWeatherRepo: DailyWeatherRepo
) : BaseLocalUseCaseWithParam<Long, List<DailyWeatherEntity>> {

    override fun execute(param: Long): Flow<List<DailyWeatherEntity>> {
        return dailyWeatherRepo.getDailyWeatherList(param)
    }
}