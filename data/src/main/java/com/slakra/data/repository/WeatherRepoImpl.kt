package com.slakra.data.repository

import com.slakra.common.utils.CoroutineContextProvider
import com.slakra.data.BuildConfig
import com.slakra.data.local.CurrentWeatherDao
import com.slakra.data.local.DailyWeatherDao
import com.slakra.data.local.HourlyWeatherDao
import com.slakra.data.mapper.remote.CurrentWeatherMapper
import com.slakra.data.mapper.remote.DailyWeatherMapper
import com.slakra.data.mapper.remote.HourlyWeatherMapper
import com.slakra.data.remote.model.CurrentWeatherRemoteModel
import com.slakra.data.remote.model.DailyWeatherRemoteModel
import com.slakra.data.remote.model.HourlyWeatherRemoteModel
import com.slakra.data.remote.model.WeatherResponse
import com.slakra.data.remote.WeatherService
import com.slakra.common.ResultState
import com.slakra.domain.repository.GetWeatherRequest
import com.slakra.domain.repository.WeatherRepo
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * Implementation of [WeatherRepo] to get weather details from server
 * and process weather data
 * @author sumitlakra
 * @date 06/04/2021
 */
class WeatherRepoImpl(
    private val remote: WeatherService,
    private val currentWeatherDao: CurrentWeatherDao,
    private val dailyWeatherDao: DailyWeatherDao,
    private val hourlyWeatherDao: HourlyWeatherDao,
    private val currentWeatherMapper: CurrentWeatherMapper,
    private val dailyWeatherMapper: DailyWeatherMapper,
    private val hourlyWeatherMapper: HourlyWeatherMapper,
    private val contextProvider: CoroutineContextProvider
) : WeatherRepo {

    override suspend fun getWeatherDetails(request: GetWeatherRequest): ResultState<Boolean> =
        withContext(contextProvider.IO){
            try {
                val response = remote.getCurrentAndForecastWeather(
                    request.lat,
                    request.lon,
                    request.exclude,
                    request.units,
                    BuildConfig.API_KEY
                )
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        processWeatherData(body)
                        ResultState.Success(true)
                    }else {
                        ResultState.InvalidData
                    }
                }else {
                    when (response.code()) {
                        403 -> ResultState.HttpErrors.ResourceForbidden(response.message())
                        404 -> ResultState.HttpErrors.ResourceNotFound(response.message())
                        500 -> ResultState.HttpErrors.InternalServerError(response.message())
                        502 -> ResultState.HttpErrors.BadGateWay(response.message())
                        301 -> ResultState.HttpErrors.ResourceRemoved(response.message())
                        302 -> ResultState.HttpErrors.RemovedResourceFound(response.message())
                        else -> ResultState.Error(response.message())
                    }
                }
            }catch (ex: Exception) {
                ResultState.NetworkException(ex.localizedMessage!!)
            }
    }

    private fun processWeatherData(response: WeatherResponse) {
        saveCurrentWeatherData(response.currentWeatherModel)
        saveDailyWeatherData(response.dailyWeatherRemoteList)
        saveHourlyWeatherData(response.hourlyWeatherList)
    }

    private fun saveCurrentWeatherData(currentWeatherModel: CurrentWeatherRemoteModel) {
        currentWeatherDao.insertEntityWithReplaceStrategy(currentWeatherMapper.map(currentWeatherModel))
    }

    private fun saveDailyWeatherData(dailyWeatherRemoteList: List<DailyWeatherRemoteModel>) {
        dailyWeatherDao.clearTable()
        dailyWeatherDao.insertAll(dailyWeatherRemoteList.map {
            dailyWeatherMapper.map(it)
        })
    }

    private fun saveHourlyWeatherData(hourlyWeatherList: List<HourlyWeatherRemoteModel>) {
        hourlyWeatherDao.clearTable()
        hourlyWeatherDao.insertAll(hourlyWeatherList.map {
            hourlyWeatherMapper.map(it)
        })
    }
}