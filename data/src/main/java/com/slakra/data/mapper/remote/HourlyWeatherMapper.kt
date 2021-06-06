package com.slakra.data.mapper.remote

import com.slakra.data.local.model.HourlyWeatherModel
import com.slakra.data.remote.model.HourlyWeatherRemoteModel
import kotlin.math.roundToInt

/**
 * Mapper class to map [HourlyWeatherRemoteModel] to [HourlyWeatherModel]
 * @author sumitlakra
 * @date 06/04/2021
 */
class HourlyWeatherMapper: RemoteToModelMapper<HourlyWeatherRemoteModel, HourlyWeatherModel> {

    override fun map(remote: HourlyWeatherRemoteModel): HourlyWeatherModel {
        return HourlyWeatherModel(
            timeStamp = remote.timeStamp,
            temp = remote.temp.roundToInt().toString(),
            weather = remote.weatherModelList[0]
        )
    }
}