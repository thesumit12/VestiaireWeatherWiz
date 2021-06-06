package com.slakra.domain.repository

import com.slakra.domain.entity.DailyWeatherEntity
import kotlinx.coroutines.flow.Flow

/**
 * Interface to provide Daily weather business logic
 * @author sumitlakra
 * @date 06/04/2021
 */
interface DailyWeatherRepo {

    fun getDailyWeatherList(param: Long): Flow<List<DailyWeatherEntity>>
}