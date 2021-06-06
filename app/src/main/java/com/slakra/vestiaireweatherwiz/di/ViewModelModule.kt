package com.slakra.vestiaireweatherwiz.di

import com.slakra.vestiaireweatherwiz.MainViewModel
import com.slakra.vestiaireweatherwiz.weeklyforecast.WeeklyForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module to provide ViewModel dependencies
 * @author sumitlakra
 * @date 06/05/2021
 */
internal val viewModelModule = module {

    viewModel { MainViewModel(get(), get(), get(), get()) }
    viewModel { WeeklyForecastViewModel(get()) }
}