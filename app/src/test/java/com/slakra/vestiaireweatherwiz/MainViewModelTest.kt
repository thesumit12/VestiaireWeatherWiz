package com.slakra.vestiaireweatherwiz

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.slakra.common.ProgressState
import com.slakra.common.utils.CoroutineContextProvider
import com.slakra.common.ResultState
import com.slakra.domain.entity.HourlyWeatherEntity
import com.slakra.domain.usecase.GetCurrentWeatherUseCase
import com.slakra.domain.usecase.GetHourlyWeatherUseCase
import com.slakra.domain.usecase.GetWeatherDetailsFromServerUseCase
import io.mockk.coEvery
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
class MainViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun refreshWeatherDataTest() {
        val getWeatherDetailsFromServerUseCase: GetWeatherDetailsFromServerUseCase = mockk()
        val getCurrentWeatherUseCase: GetCurrentWeatherUseCase = mockk()
        val hourlyWeatherUseCase: GetHourlyWeatherUseCase = mockk()
        val contextProvider: CoroutineContextProvider = TestContextProvider()
        var mockResult: ResultState<Boolean> = ResultState.Success(true)
        testCoroutineRule.runBlockingTest {
            coEvery { getWeatherDetailsFromServerUseCase.execute(any()) } returns mockResult

            var viewModel = MainViewModel(
                getWeatherDetailsFromServerUseCase,
                getCurrentWeatherUseCase,
                hourlyWeatherUseCase,
                contextProvider
            )
            viewModel.refreshWeatherData()

            this.advanceUntilIdle()
            assertEquals(viewModel.state.value, ProgressState.SUCCESS)

            mockResult = ResultState.InvalidData
            coEvery { getWeatherDetailsFromServerUseCase.execute(any()) } returns mockResult
            viewModel = MainViewModel(
                getWeatherDetailsFromServerUseCase,
                getCurrentWeatherUseCase,
                hourlyWeatherUseCase,
                contextProvider
            )
            viewModel.refreshWeatherData()
            this.advanceUntilIdle()
            assertEquals(viewModel.state.value, ProgressState.ERROR)
        }
    }

    @Test
    fun when_getHourlyWeatherData_returns_ListHourlyWeatherEntity_test() {
        val getWeatherDetailsFromServerUseCase: GetWeatherDetailsFromServerUseCase = mockk()
        val getCurrentWeatherUseCase: GetCurrentWeatherUseCase = mockk()
        val hourlyWeatherUseCase: GetHourlyWeatherUseCase = mockk()
        val contextProvider: CoroutineContextProvider = TestContextProvider()

        val mockList: List<HourlyWeatherEntity> = mockk()
        every { mockList.size } returns 24
        val mockResult: Flow<List<HourlyWeatherEntity>> = flowOf(mockList)

        every { hourlyWeatherUseCase.execute(any()) } returns mockResult

        val viewModel = MainViewModel(
            getWeatherDetailsFromServerUseCase,
            getCurrentWeatherUseCase,
            hourlyWeatherUseCase,
            contextProvider
        )
        val res = viewModel.getHourlyWeatherData()

        testCoroutineRule.runBlockingTest {
            res.collect {
                assertEquals(it.size, 24)
            }
        }
    }
}