package com.slakra.data.mapper.remote

import com.slakra.data.local.model.CurrentWeatherModel
import com.slakra.data.remote.model.CurrentWeatherRemoteModel
import kotlin.math.roundToInt

/**
 * Mapper class to map [CurrentWeatherRemoteModel] to [CurrentWeatherModel]
 * @author sumitlakra
 * @date 06/04/2021
 */
class CurrentWeatherMapper: RemoteToModelMapper<CurrentWeatherRemoteModel, CurrentWeatherModel> {

    override fun map(remote: CurrentWeatherRemoteModel): CurrentWeatherModel {
        return CurrentWeatherModel(
            timeStamp = remote.timeStamp,
            sunrise = remote.sunrise,
            sunset = remote.sunset,
            temp = remote.temp.roundToInt().toString(),
            feels_like = remote.feels_like.roundToInt().toString(),
            pressure = remote.pressure,
            humidity = remote.humidity,
            uvIndex = remote.uvIndex,
            wind_speed = remote.wind_speed.roundToInt().toString(),
            weather = remote.weatherModelList[0],
            visibility = remote.visibility,
            dew_point = remote.dew_point.roundToInt().toString()
        )
    }
}