package com.slakra.data.di

import com.slakra.data.local.AppDatabase
import org.koin.dsl.module

/**
 * Koin module to provide Room database dependencies
 * @author sumitlakra
 * @date 06/04/2021
 */
internal val dbModule = module {
    single { AppDatabase(get()) }
    factory { getCurrentWeatherDao(get()) }
    factory { getDailyWeatherDao(get()) }
    factory { getHourlyWeatherDao(get()) }
}

fun getCurrentWeatherDao(appDatabase: AppDatabase) = appDatabase.currentWeatherDao()
fun getDailyWeatherDao(appDatabase: AppDatabase) = appDatabase.dailyWeatherDao()
fun getHourlyWeatherDao(appDatabase: AppDatabase) = appDatabase.hourlyWeatherDao()