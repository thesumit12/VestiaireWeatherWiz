package com.slakra.vestiaireweatherwiz

import androidx.lifecycle.viewModelScope
import com.slakra.common.BaseViewModel
import com.slakra.common.ProgressState
import com.slakra.common.utils.AppConstants
import com.slakra.common.utils.CoroutineContextProvider
import com.slakra.common.ResultState
import com.slakra.domain.entity.CurrentWeatherEntity
import com.slakra.domain.entity.HourlyWeatherEntity
import com.slakra.domain.repository.GetWeatherRequest
import com.slakra.domain.usecase.GetCurrentWeatherUseCase
import com.slakra.domain.usecase.GetHourlyWeatherUseCase
import com.slakra.domain.usecase.GetWeatherDetailsFromServerUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * ViewModel to handle home screen data
 * @author sumitlakra
 * @date 06/05/2021
 */
class MainViewModel(
    private val getWeatherDetailsFromServerUseCase: GetWeatherDetailsFromServerUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val hourlyWeatherUseCase: GetHourlyWeatherUseCase,
    private val contextProvider: CoroutineContextProvider
): BaseViewModel() {

    val currentWeatherFlow: Flow<ResultState<CurrentWeatherEntity>>
    get() = getCurrentWeatherUseCase.execute()

    /**
     * Function to get weather data from server
     * @author sumitlakra
     * @date 06/06/2021
     */
    fun refreshWeatherData() {
        _state.value = ProgressState.LOADING
        viewModelScope.launch(contextProvider.Main) {

            val result = getWeatherDetailsFromServerUseCase.execute(
                GetWeatherRequest("48.55","2.35",
                    "minutely","metric")
            )
            when(result) {
                is ResultState.Success<Boolean> -> {
                    _state.value = ProgressState.SUCCESS
                }
                else -> {
                    parseError(result)
                }
            }
        }
    }

    /**
     * Function to get hourly weather data from db
     * @return [Flow<List<HourlyWeatherEntity>>]
     * @author sumitlakra
     * @date 06/05/2021
     */
    fun getHourlyWeatherData(): Flow<List<HourlyWeatherEntity>> {
        return hourlyWeatherUseCase.execute((System.currentTimeMillis() - AppConstants.ONE_HOUR)/1000)
    }
}