package com.slakra.data.mapper.remote

import com.slakra.data.local.model.DailyWeatherModel
import com.slakra.data.remote.model.DailyWeatherRemoteModel

/**
 * Mapper class to map [DailyWeatherRemoteModel] to [DailyWeatherModel]
 * @author sumitlakra
 * @date 06/04/2021
 */
class DailyWeatherMapper: RemoteToModelMapper<DailyWeatherRemoteModel, DailyWeatherModel> {

    override fun map(remote: DailyWeatherRemoteModel): DailyWeatherModel {
        return DailyWeatherModel(
            timeStamp = remote.timeStamp,
            weather = remote.weatherModelList[0],
            temperature_model = remote.temperatureModel,
            pressure = remote.pressure,
            humidity = remote.humidity,
            wind_speed = remote.wind_speed,
            uvIndex = remote.uvIndex
        )
    }
}