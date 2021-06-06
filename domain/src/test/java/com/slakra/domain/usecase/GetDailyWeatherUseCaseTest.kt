package com.slakra.domain.usecase

import com.slakra.domain.entity.DailyWeatherEntity
import com.slakra.domain.repository.DailyWeatherRepo
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert.*
import org.junit.Test

class GetDailyWeatherUseCaseTest {

    @Test
    fun when_getDailyWeatherList_returns_ListDailyWeatherEntity_test() {
        val mockRepo: DailyWeatherRepo = mockk()
        val mockList: List<DailyWeatherEntity> = mockk()
        val mockResult: Flow<List<DailyWeatherEntity>> = flow {
            emit(mockList)
        }
        every { mockRepo.getDailyWeatherList(any()) } returns mockResult

        val useCase = GetDailyWeatherUseCase(mockRepo)
        val res = useCase.execute(1L)
        assertEquals(res, mockResult)
    }
}