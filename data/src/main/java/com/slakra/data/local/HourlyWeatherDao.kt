package com.slakra.data.local

import androidx.room.Dao
import androidx.room.Query
import com.slakra.data.local.model.HourlyWeatherModel
import kotlinx.coroutines.flow.Flow

/**
 * Dao class for Hourly weather functionality
 * @author sumitlakra
 * @date 06/04/2021
 */
@Dao
interface HourlyWeatherDao: RoomBaseDao<HourlyWeatherModel> {

    @Query("SELECT * FROM hourly_weather WHERE timeStamp > :time LIMIT 24")
    fun getHourlyWeather(time: Long): Flow<List<HourlyWeatherModel>>

    @Query("DELETE FROM hourly_weather")
    fun clearTable()
}