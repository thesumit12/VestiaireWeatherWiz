package com.slakra.data.di

import com.slakra.data.mapper.entity.CurrentWeatherEntityMapper
import com.slakra.data.mapper.entity.DailyWeatherEntityMapper
import com.slakra.data.mapper.entity.HourlyWeatherEntityMapper
import com.slakra.data.mapper.remote.CurrentWeatherMapper
import com.slakra.data.mapper.remote.DailyWeatherMapper
import com.slakra.data.mapper.remote.HourlyWeatherMapper
import org.koin.dsl.module

/**
 * Koin module to provide App Mapper dependencies
 * @author sumitlakra
 * @date 06/04/2021
 */
internal val mapperModule = module {

    factory { CurrentWeatherMapper() }
    factory { DailyWeatherMapper() }
    factory { HourlyWeatherMapper() }

    factory { CurrentWeatherEntityMapper() }
    factory { DailyWeatherEntityMapper() }
    factory { HourlyWeatherEntityMapper() }
}