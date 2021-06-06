package com.slakra.data.mapper

import com.slakra.data.remote.model.WeatherModel
import com.slakra.domain.entity.WeatherEntity

/**
 * Extension function to convert [WeatherModel] to [WeatherEntity]
 * @author sumitlakra
 * @date 06/04/2021
 */
fun WeatherModel.toEntity(): WeatherEntity {
    return WeatherEntity(
        weatherCode = this.code,
        main = this.main,
        description = this.description
    )
}