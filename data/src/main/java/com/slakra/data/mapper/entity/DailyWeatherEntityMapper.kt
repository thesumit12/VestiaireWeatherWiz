package com.slakra.data.mapper.entity

import com.slakra.data.local.model.DailyWeatherModel
import com.slakra.domain.entity.DailyWeatherEntity
import kotlin.math.roundToInt

/**
 * Mapper class to map [DailyWeatherModel] to [DailyWeatherEntity]
 * @author sumitlakra
 * @date 06/04/2021
 */
class DailyWeatherEntityMapper: ModelToEntityMapper<DailyWeatherModel, DailyWeatherEntity> {

    override fun map(model: DailyWeatherModel): DailyWeatherEntity {
        return DailyWeatherEntity(
            timeStamp = model.timeStamp,
            weatherCode = model.weather.code,
            minTemp = model.temperature_model.min.toDouble().roundToInt().toString(),
            maxTemp = model.temperature_model.max.toDouble().roundToInt().toString(),
            pressure = model.pressure,
            humidity = model.humidity,
            windSpeed = model.wind_speed,
            uvIndex = model.uvIndex
        )
    }
}