package com.slakra.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.slakra.data.local.model.CurrentWeatherModel
import com.slakra.data.local.model.DailyWeatherModel
import com.slakra.data.local.model.HourlyWeatherModel

private const val DB_NAME = "vestiaireWeather.db"

@Database(
    entities = [CurrentWeatherModel::class, DailyWeatherModel::class, HourlyWeatherModel::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun dailyWeatherDao(): DailyWeatherDao
    abstract fun hourlyWeatherDao(): HourlyWeatherDao

    companion object {
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, DB_NAME
        ).build()
    }
}