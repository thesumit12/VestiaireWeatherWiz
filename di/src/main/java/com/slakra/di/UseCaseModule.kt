package com.slakra.di

import com.slakra.domain.usecase.GetCurrentWeatherUseCase
import com.slakra.domain.usecase.GetDailyWeatherUseCase
import com.slakra.domain.usecase.GetHourlyWeatherUseCase
import com.slakra.domain.usecase.GetWeatherDetailsFromServerUseCase
import org.koin.dsl.module

/**
 * Koin module to provide UseCase dependencies
 * @author sumitlakra
 * @date 06/04/2021
 */
internal val useCaseModule = module {

    factory { GetCurrentWeatherUseCase(currentWeatherRepo = get()) }
    factory { GetDailyWeatherUseCase(dailyWeatherRepo = get()) }
    factory { GetHourlyWeatherUseCase(hourlyWeatherRepo = get()) }
    factory { GetWeatherDetailsFromServerUseCase(weatherRepo = get()) }
}