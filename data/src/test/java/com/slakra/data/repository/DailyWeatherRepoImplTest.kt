package com.slakra.data.repository

import com.slakra.data.TestCoroutineRule
import com.slakra.data.local.DailyWeatherDao
import com.slakra.data.local.model.DailyWeatherModel
import com.slakra.data.mapper.entity.DailyWeatherEntityMapper
import com.slakra.data.remote.model.TemperatureRemoteModel
import com.slakra.data.remote.model.WeatherModel
import com.slakra.domain.entity.DailyWeatherEntity
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
class DailyWeatherRepoImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun when_getDailyWeatherList_return_ListDailyWeatherEntity_test() {
        val dao: DailyWeatherDao = mockk()
        val mapper = DailyWeatherEntityMapper()
        val mockResult = createMockResult()

        val dailyWeatherEntity = DailyWeatherEntity(
            1L, 100,
            "20", "30",
            "pressure", "humidity",
            "wind", "uvIndex")

        every { dao.getDailyWeatherList(any()) } returns mockResult
        val repo = DailyWeatherRepoImpl(dao, mapper)
        testCoroutineRule.runBlockingTest {
            val res = repo.getDailyWeatherList(1L)
            res.collect {
                assertEquals(it.size, 3)
                assertEquals(it[0], dailyWeatherEntity)
            }
        }
    }

    private fun createMockResult(): Flow<List<DailyWeatherModel>> = flow {
        val weatherModel = WeatherModel(100, "main", "description")
        val tempModel = TemperatureRemoteModel("20.33", "30.12")
        val item1 = DailyWeatherModel(
            1L,
            weatherModel,
            tempModel,
            "pressure",
            "humidity",
            "uvIndex",
            "wind"
        )
        val item2 = DailyWeatherModel(
            2L,
            weatherModel,
            tempModel,
            "pressure2",
            "humidity2",
            "uvIndex2",
            "wind2"
        )
        val item3 = DailyWeatherModel(
            3L,
            weatherModel,
            tempModel,
            "pressure3",
            "humidity3",
            "uvIndex3",
            "wind4"
        )
        emit(listOf(item1, item2, item3))
    }
}