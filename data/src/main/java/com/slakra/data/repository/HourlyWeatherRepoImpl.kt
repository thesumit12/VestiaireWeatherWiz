package com.slakra.data.repository

import com.slakra.data.local.HourlyWeatherDao
import com.slakra.data.mapper.entity.HourlyWeatherEntityMapper
import com.slakra.data.mapper.remote.HourlyWeatherMapper
import com.slakra.domain.entity.HourlyWeatherEntity
import com.slakra.domain.repository.HourlyWeatherRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of [HourlyWeatherRepo] interface methods
 * @author sumitlakra
 * @date 06/05/2021
 */
class HourlyWeatherRepoImpl(
    private val hourlyWeatherDao: HourlyWeatherDao,
    private val hourlyWeatherEntityMapper: HourlyWeatherEntityMapper
): HourlyWeatherRepo {

    override fun getHourlyWeatherDetails(param: Long): Flow<List<HourlyWeatherEntity>> {
        return hourlyWeatherDao.getHourlyWeather(param).map { list->
            list.map { hourlyWeatherModel ->
                hourlyWeatherEntityMapper.map(hourlyWeatherModel)
            }
        }
    }
}