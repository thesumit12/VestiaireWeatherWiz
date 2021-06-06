package com.slakra.domain.usecase

import com.slakra.domain.entity.HourlyWeatherEntity
import com.slakra.domain.repository.HourlyWeatherRepo
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import org.junit.Assert.*
import org.junit.Test

class GetHourlyWeatherUseCaseTest {

    @Test
    fun when_getHourlyWeather_returns_ListHourlyWeatherEntity_test() {
        val mockRepo: HourlyWeatherRepo = mockk()
        val mockResult: Flow<List<HourlyWeatherEntity>> = mockk()

        every { mockRepo.getHourlyWeatherDetails(any()) } returns mockResult
        val useCase = GetHourlyWeatherUseCase(mockRepo)
        val res = useCase.execute(1L)
        assertEquals(res, mockResult)
    }
}