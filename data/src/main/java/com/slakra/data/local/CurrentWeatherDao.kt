package com.slakra.data.local

import androidx.room.Dao
import androidx.room.Query
import com.slakra.data.local.model.CurrentWeatherModel
import kotlinx.coroutines.flow.Flow

/**
 * Dao class for Current weather functionality
 * @author sumitlakra
 * @date 06/04/2021
 */
@Dao
interface CurrentWeatherDao: RoomBaseDao<CurrentWeatherModel> {

    @Query("SELECT * FROM current_weather LIMIT 1")
    fun getCurrentWeatherDetails(): Flow<CurrentWeatherModel?>
}