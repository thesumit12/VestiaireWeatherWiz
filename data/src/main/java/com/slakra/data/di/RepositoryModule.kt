package com.slakra.data.di

import com.slakra.data.repository.CurrentWeatherRepoImpl
import com.slakra.data.repository.DailyWeatherRepoImpl
import com.slakra.data.repository.HourlyWeatherRepoImpl
import com.slakra.data.repository.WeatherRepoImpl
import com.slakra.domain.repository.CurrentWeatherRepo
import com.slakra.domain.repository.DailyWeatherRepo
import com.slakra.domain.repository.HourlyWeatherRepo
import com.slakra.domain.repository.WeatherRepo
import org.koin.dsl.module

/**
 * Koin module to provide repository dependencies
 * @author sumitlakra
 * @date 06/04/2021
 */
internal val repositoryModule = module {

    factory<WeatherRepo> { WeatherRepoImpl(get(), get(), get(), get(), get(), get(), get(), get()) }
    factory<CurrentWeatherRepo> { CurrentWeatherRepoImpl(get(), get()) }
    factory<DailyWeatherRepo> { DailyWeatherRepoImpl(get(), get()) }
    factory<HourlyWeatherRepo> { HourlyWeatherRepoImpl(get(), get()) }
}