package com.slakra.vestiaireweatherwiz.weeklyforecast

import com.slakra.common.BaseViewModel
import com.slakra.domain.entity.DailyWeatherEntity
import com.slakra.domain.usecase.GetDailyWeatherUseCase
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel to handle WeeklyForecast data
 * @author sumitlakra
 * @date 06/05/2021
 */
class WeeklyForecastViewModel(
    private val dailyWeatherUseCase: GetDailyWeatherUseCase
): BaseViewModel() {

    /**
     * Function to get weekly forecast data
     * @return [Flow<List<DailyWeatherEntity>>]
     * @author sumitlakra
     * @date 06/05/2021
     */
    fun getDailyWeatherData(): Flow<List<DailyWeatherEntity>> {
        return dailyWeatherUseCase.execute(System.currentTimeMillis()/1000)
    }
}