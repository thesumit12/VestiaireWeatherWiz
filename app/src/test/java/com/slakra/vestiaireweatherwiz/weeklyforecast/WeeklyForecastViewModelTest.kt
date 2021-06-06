package com.slakra.vestiaireweatherwiz.weeklyforecast

import com.slakra.domain.entity.DailyWeatherEntity
import com.slakra.domain.usecase.GetDailyWeatherUseCase
import com.slakra.vestiaireweatherwiz.TestCoroutineRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WeeklyForecastViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun when_getDailyWeatherData_return_ListDailyWeatherEntity_test() {
        val dailyWeatherUseCase: GetDailyWeatherUseCase = mockk()
        val mockList: List<DailyWeatherEntity> = mockk()
        every { mockList.size } returns 6
        val mockResult: Flow<List<DailyWeatherEntity>> = flowOf(mockList)
        every { dailyWeatherUseCase.execute(any()) } returns mockResult
        val viewModel = WeeklyForecastViewModel(dailyWeatherUseCase)
        val res = viewModel.getDailyWeatherData()
        testCoroutineRule.runBlockingTest {
            res.collect {
                assertEquals(it.size, 6)
            }
        }
    }
}