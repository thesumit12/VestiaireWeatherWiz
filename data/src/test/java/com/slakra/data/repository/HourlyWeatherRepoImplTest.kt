package com.slakra.data.repository

import com.slakra.data.TestCoroutineRule
import com.slakra.data.local.HourlyWeatherDao
import com.slakra.data.local.model.DailyWeatherModel
import com.slakra.data.local.model.HourlyWeatherModel
import com.slakra.data.mapper.entity.HourlyWeatherEntityMapper
import com.slakra.data.remote.model.TemperatureRemoteModel
import com.slakra.data.remote.model.WeatherModel
import com.slakra.domain.entity.HourlyWeatherEntity
import com.slakra.domain.entity.WeatherEntity
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
class HourlyWeatherRepoImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun when_getHourlyWeatherDetails_returns_ListHourlyWeatherEntity_test() {
        val dao: HourlyWeatherDao = mockk()
        val mapper = HourlyWeatherEntityMapper()
        val mockResult = createMockResult()
        val hourlyWeatherEntity = HourlyWeatherEntity(2L,"33",
            WeatherEntity(100,"main", "description"))

        every { dao.getHourlyWeather(any()) } returns mockResult

        val repo = HourlyWeatherRepoImpl(dao, mapper)
        val res = repo.getHourlyWeatherDetails(1L)
        testCoroutineRule.runBlockingTest {
            res.collect {
                assertEquals(it.size, 3)
                assertEquals(it[1], hourlyWeatherEntity)
            }
        }
    }

    private fun createMockResult(): Flow<List<HourlyWeatherModel>> = flow {
        val weatherModel = WeatherModel(100, "main", "description")
        val item1 = HourlyWeatherModel(
            1L,
            "30",
            weatherModel
        )
        val item2 = HourlyWeatherModel(
            2L,
            "33",
            weatherModel
        )
        val item3 = HourlyWeatherModel(
            3L,
            "20",
            weatherModel
        )
        emit(listOf(item1, item2, item3))
    }
}