package com.slakra.data.repository

import com.slakra.data.TestCoroutineRule
import com.slakra.data.local.CurrentWeatherDao
import com.slakra.data.local.model.CurrentWeatherModel
import com.slakra.data.mapper.entity.CurrentWeatherEntityMapper
import com.slakra.data.remote.model.WeatherModel
import com.slakra.common.ResultState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CurrentWeatherRepoImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun when_getCurrentWeather_returns_success_test() {
        val dao: CurrentWeatherDao = mockk()
        val mapper = CurrentWeatherEntityMapper()
        val mockResult = createMockResult()
        every { dao.getCurrentWeatherDetails() } returns mockResult

        val repo = CurrentWeatherRepoImpl(dao, mapper)
        val res = repo.getCurrentWeather()
        testCoroutineRule.runBlockingTest {
            res.collect {
                assert(it is ResultState.Success)
                assertEquals((it as ResultState.Success).data.temp, "temp")
            }
        }
    }

    @Test
    fun when_getCurrentWeather_returns_error_test() {
        val dao: CurrentWeatherDao = mockk()
        val mapper = CurrentWeatherEntityMapper()
        val mockResult: Flow<CurrentWeatherModel?> = flowOf(null)
        every { dao.getCurrentWeatherDetails() } returns mockResult

        val repo = CurrentWeatherRepoImpl(dao, mapper)
        val res = repo.getCurrentWeather()
        testCoroutineRule.runBlockingTest {
            res.collect {
                assert(it is ResultState.InvalidData)
            }
        }
    }

    private fun createMockResult(): Flow<CurrentWeatherModel> =
        flow {
            val weatherEntity = WeatherModel(100, "main", "description")
            val currentWeatherModel = CurrentWeatherModel(
                1L,
                1L,
                1L,
                "temp",
                "feelsLike",
                "pressure",
                "humidity",
                "uvIndex",
                "windSpeed",
                weatherEntity,
                "visibility",
                "dewPoint"
            )
            emit(currentWeatherModel)
        }
}