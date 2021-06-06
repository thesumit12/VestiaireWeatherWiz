package com.slakra.data.mapper.entity

import com.slakra.data.local.model.CurrentWeatherModel
import com.slakra.data.mapper.toEntity
import com.slakra.domain.entity.CurrentWeatherEntity

/**
 * Mapper class to map [CurrentWeatherModel] to [CurrentWeatherEntity]
 * @author sumitlakra
 * @date 06/04/2021
 */
class CurrentWeatherEntityMapper: ModelToEntityMapper<CurrentWeatherModel, CurrentWeatherEntity> {

    override fun map(model: CurrentWeatherModel): CurrentWeatherEntity {
        return CurrentWeatherEntity(
            timeStamp = model.timeStamp,
            sunrise = model.sunrise,
            sunset = model.sunset,
            temp = model.temp,
            feelsLike = model.feels_like,
            pressure = model.pressure,
            humidity = model.humidity,
            uvIndex = model.uvIndex,
            windSpeed = model.wind_speed,
            weatherEntity = model.weather.toEntity(),
            visibility = model.visibility,
            dewPoint = model.dew_point
        )
    }
}