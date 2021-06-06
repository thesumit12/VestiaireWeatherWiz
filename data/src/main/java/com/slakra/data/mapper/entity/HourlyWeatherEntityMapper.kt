package com.slakra.data.mapper.entity

import com.slakra.data.local.model.HourlyWeatherModel
import com.slakra.data.mapper.toEntity
import com.slakra.domain.entity.HourlyWeatherEntity

/**
 * Mapper class to map [HourlyWeatherModel] to [HourlyWeatherEntity]
 * @author sumitlakra
 * @date 06/04/2021
 */
class HourlyWeatherEntityMapper: ModelToEntityMapper<HourlyWeatherModel, HourlyWeatherEntity> {

    override fun map(model: HourlyWeatherModel): HourlyWeatherEntity {
        return HourlyWeatherEntity(
            timestamp = model.timeStamp,
            temp = model.temp,
            weatherEntity = model.weather.toEntity()
        )
    }
}