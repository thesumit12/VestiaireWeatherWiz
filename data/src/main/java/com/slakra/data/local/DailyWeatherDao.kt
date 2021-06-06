package com.slakra.data.local

import androidx.room.Dao
import androidx.room.Query
import com.slakra.data.local.model.DailyWeatherModel
import kotlinx.coroutines.flow.Flow

/**
 * Dao class for Daily weather functionality
 * @author sumitlakra
 * @date 06/04/2021
 */
@Dao
interface DailyWeatherDao: RoomBaseDao<DailyWeatherModel> {

    @Query("SELECT * FROM daily_weather WHERE timeStamp > :time LIMIT 7")
    fun getDailyWeatherList(time: Long): Flow<List<DailyWeatherModel>>

    @Query("DELETE FROM daily_weather")
    fun clearTable()
}