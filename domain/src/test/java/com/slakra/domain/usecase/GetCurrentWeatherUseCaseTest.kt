package com.slakra.domain.usecase

import com.slakra.domain.TestCoroutineRule
import com.slakra.common.ResultState
import com.slakra.domain.entity.CurrentWeatherEntity
import com.slakra.domain.entity.WeatherEntity
import com.slakra.domain.repository.CurrentWeatherRepo
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCurrentWeatherUseCaseTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun when_getCurrentWeather_returns_success_test() {
        val repo: CurrentWeatherRepo = mockk()
        val mockResult = createMockResult(true)
        every { repo.getCurrentWeather() } returns mockResult
        val useCase = GetCurrentWeatherUseCase(repo)
        val res = useCase.execute()
        testCoroutineRule.runBlockingTest {
            res.collect {
                assert(it is ResultState.Success)
                assertEquals((it as ResultState.Success).data.temp, "temp")
            }
        }
    }

    @Test
    fun when_getCurrentWeather_returns_error_test() {
        val repo: CurrentWeatherRepo = mockk()
        val mockResult = createMockResult(false)
        every { repo.getCurrentWeather() } returns mockResult
        val useCase = GetCurrentWeatherUseCase(repo)
        val res = useCase.execute()
        testCoroutineRule.runBlockingTest {
            res.collect {
                assert(it is ResultState.Error)
                assertEquals((it as ResultState.Error).errorMsg, "Test_Error")
            }
        }
    }

    private fun createMockResult(isSuccess: Boolean): Flow<ResultState<CurrentWeatherEntity>> =
        flow {
            val weatherEntity = WeatherEntity(100, "main", "description")
            val currentWeatherEntity = CurrentWeatherEntity(
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
            if (isSuccess) {
                emit(ResultState.Success(currentWeatherEntity))
            }else {
                emit(ResultState.Error("Test_Error"))
            }
        }
}