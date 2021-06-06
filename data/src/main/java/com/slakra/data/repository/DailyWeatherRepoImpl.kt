package com.slakra.data.repository

import com.slakra.data.local.DailyWeatherDao
import com.slakra.data.mapper.entity.DailyWeatherEntityMapper
import com.slakra.domain.entity.DailyWeatherEntity
import com.slakra.domain.repository.DailyWeatherRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of [DailyWeatherRepo] interface methods
 * @author sumitlakra
 * @date 06/05/2021
 */
class DailyWeatherRepoImpl(
    private val dailyWeatherDao: DailyWeatherDao,
    private val dailyWeatherEntityMapper: DailyWeatherEntityMapper
): DailyWeatherRepo {

    override fun getDailyWeatherList(param: Long): Flow<List<DailyWeatherEntity>> {
        return dailyWeatherDao.getDailyWeatherList(param).map { list->
            list.map { dailyWeatherModel ->
                dailyWeatherEntityMapper.map(dailyWeatherModel)
            }
        }
    }
}