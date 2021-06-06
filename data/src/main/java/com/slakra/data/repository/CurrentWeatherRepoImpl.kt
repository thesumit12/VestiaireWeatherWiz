package com.slakra.data.repository

import com.slakra.data.local.CurrentWeatherDao
import com.slakra.data.mapper.entity.CurrentWeatherEntityMapper
import com.slakra.common.ResultState
import com.slakra.domain.entity.CurrentWeatherEntity
import com.slakra.domain.repository.CurrentWeatherRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of [CurrentWeatherRepo] interface methods
 * @author sumitlakra
 * @date 06/05/2021
 */
class CurrentWeatherRepoImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val currentWeatherEntityMapper: CurrentWeatherEntityMapper
): CurrentWeatherRepo {

    override fun getCurrentWeather(): Flow<ResultState<CurrentWeatherEntity>> {
        return currentWeatherDao.getCurrentWeatherDetails().map {
            if (it != null) {
                ResultState.Success(currentWeatherEntityMapper.map(it))
            }else {
                ResultState.InvalidData
            }
        }
    }
}