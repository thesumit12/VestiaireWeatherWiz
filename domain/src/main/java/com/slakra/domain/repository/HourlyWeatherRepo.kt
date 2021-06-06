package com.slakra.domain.repository

import com.slakra.domain.entity.HourlyWeatherEntity
import kotlinx.coroutines.flow.Flow

/**
 * Interface to provide hourly weather business logic
 * @author sumitlakra
 * @date 06/04/2021
 */
interface HourlyWeatherRepo {

    fun getHourlyWeatherDetails(param: Long): Flow<List<HourlyWeatherEntity>>
}