package com.slakra.domain.usecase

import com.slakra.domain.TestCoroutineRule
import com.slakra.common.ResultState
import com.slakra.domain.repository.GetWeatherRequest
import com.slakra.domain.repository.WeatherRepo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetWeatherDetailsFromServerUseCaseTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun when_getWeatherDetailsFromServer_returns_success_test() {
        val mockRepo: WeatherRepo = mockk()
        val mockResult = com.slakra.common.ResultState.Success(true)
        val request: GetWeatherRequest = mockk()
        coEvery { mockRepo.getWeatherDetails(any()) } returns mockResult

        val useCase = GetWeatherDetailsFromServerUseCase(mockRepo)
        testCoroutineRule.runBlockingTest {
            val isSuccess = when(useCase.execute(request)) {
                is com.slakra.common.ResultState.Success -> true
                else -> false
            }
            assertTrue(isSuccess)
        }

    }

    @Test
    fun when_getWeatherDetailsFromServer_returns_error_test() {
        val mockRepo: WeatherRepo = mockk()
        val mockResult = com.slakra.common.ResultState.Error("Test_Error")
        val request: GetWeatherRequest = mockk()
        coEvery { mockRepo.getWeatherDetails(any()) } returns mockResult

        val useCase = GetWeatherDetailsFromServerUseCase(mockRepo)
        testCoroutineRule.runBlockingTest {
            val isSuccess = when(useCase.execute(request)) {
                is com.slakra.common.ResultState.Success -> true
                else -> false
            }
            assertFalse(isSuccess)
        }

    }
}