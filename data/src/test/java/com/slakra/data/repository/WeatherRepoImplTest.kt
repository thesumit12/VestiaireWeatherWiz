package com.slakra.data.repository

import com.slakra.common.utils.CoroutineContextProvider
import com.slakra.data.TestContextProvider
import com.slakra.data.TestCoroutineRule
import com.slakra.data.local.CurrentWeatherDao
import com.slakra.data.local.DailyWeatherDao
import com.slakra.data.local.HourlyWeatherDao
import com.slakra.data.mapper.remote.CurrentWeatherMapper
import com.slakra.data.mapper.remote.DailyWeatherMapper
import com.slakra.data.mapper.remote.HourlyWeatherMapper
import com.slakra.data.remote.WeatherService
import com.slakra.data.remote.model.*
import com.slakra.common.ResultState
import com.slakra.domain.repository.GetWeatherRequest
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class WeatherRepoImplTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun when_getWeatherDetails_returns_success_test() {
        testCoroutineRule.runBlockingTest {
            val remote: WeatherService = mockk()
            val currentWeatherDao: CurrentWeatherDao = mockk()
            val dailyWeatherDao: DailyWeatherDao = mockk()
            val hourlyWeatherDao: HourlyWeatherDao = mockk()
            val currentWeatherMapper = CurrentWeatherMapper()
            val dailyWeatherMapper = DailyWeatherMapper()
            val hourlyWeatherMapper = HourlyWeatherMapper()
            val contextProvider: CoroutineContextProvider = TestContextProvider()

            val request = GetWeatherRequest("lat", "lon", "abc", "units")
            val weatherModel = WeatherModel(100, "main", "description")
            val temperatureRemoteModel = TemperatureRemoteModel("20", "30")
            val currentWeatherModel = CurrentWeatherRemoteModel(
                1L, 1L, 1L,
                30.32, 25.15, "pressure", "humidity", "uvIndex",
                15.25, listOf(weatherModel), "visibility", 10.32
            )
            val hourlyWeatherRemoteModel = HourlyWeatherRemoteModel(2L, 20.15, listOf(weatherModel))
            val dailyWeatherRemoteModel = DailyWeatherRemoteModel(
                3L, listOf(weatherModel),
                temperatureRemoteModel, "pressure", "humidity", "uvIndex", "15.25"
            )

            val weatherResponse = WeatherResponse(
                currentWeatherModel,
                listOf(hourlyWeatherRemoteModel),
                listOf(dailyWeatherRemoteModel)
            )

            var mockResponse = Response.success(weatherResponse)
            coEvery {
                remote.getCurrentAndForecastWeather(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns mockResponse

            every { currentWeatherDao.insertEntityWithReplaceStrategy(any()) } returns 1L
            every { dailyWeatherDao.clearTable() } just runs
            every { hourlyWeatherDao.clearTable() } just runs
            every { dailyWeatherDao.insertAll(any()) } just runs
            every { hourlyWeatherDao.insertAll(any()) } just runs

            val repo = WeatherRepoImpl(
                remote,
                currentWeatherDao,
                dailyWeatherDao,
                hourlyWeatherDao,
                currentWeatherMapper,
                dailyWeatherMapper,
                hourlyWeatherMapper,
                contextProvider
            )

            var res = repo.getWeatherDetails(request)
            assert(res is ResultState.Success)

            mockResponse = Response.success(null)
            coEvery {
                remote.getCurrentAndForecastWeather(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns mockResponse

            res = repo.getWeatherDetails(request)
            assert(res is ResultState.InvalidData)
        }
    }

    @Test
    fun when_getWeatherDetails_returns_error_test() {
        testCoroutineRule.runBlockingTest {
            val remote: WeatherService = mockk()
            val currentWeatherDao: CurrentWeatherDao = mockk()
            val dailyWeatherDao: DailyWeatherDao = mockk()
            val hourlyWeatherDao: HourlyWeatherDao = mockk()
            val currentWeatherMapper = CurrentWeatherMapper()
            val dailyWeatherMapper = DailyWeatherMapper()
            val hourlyWeatherMapper = HourlyWeatherMapper()
            val contextProvider: CoroutineContextProvider = TestContextProvider()

            val request = GetWeatherRequest("lat", "lon", "abc", "units")

            val mockResponse: Response<WeatherResponse> = mockk()
            every { mockResponse.isSuccessful } returns false
            every { mockResponse.code() } returns 403
            every { mockResponse.message() } returns "forbidden"
            coEvery {
                remote.getCurrentAndForecastWeather(
                    any(),
                    any(),
                    any(),
                    any(),
                    any()
                )
            } returns mockResponse

            val repo = WeatherRepoImpl(
                remote,
                currentWeatherDao,
                dailyWeatherDao,
                hourlyWeatherDao,
                currentWeatherMapper,
                dailyWeatherMapper,
                hourlyWeatherMapper,
                contextProvider
            )

            var res = repo.getWeatherDetails(request)
            assert(res is ResultState.HttpErrors.ResourceForbidden)

            every { mockResponse.code() } returns 404
            every { mockResponse.message() } returns "ResourceNotFound"
            res = repo.getWeatherDetails(request)
            assert(res is ResultState.HttpErrors.ResourceNotFound)
            var message = (res as ResultState.HttpErrors.ResourceNotFound).exceptionMsg
            assertEquals(message, "ResourceNotFound")

            every { mockResponse.code() } returns 500
            every { mockResponse.message() } returns "InternalServerError"
            res = repo.getWeatherDetails(request)
            assert(res is ResultState.HttpErrors.InternalServerError)
            message = (res as ResultState.HttpErrors.InternalServerError).exceptionMsg
            assertEquals(message, "InternalServerError")

            every { mockResponse.code() } returns 502
            every { mockResponse.message() } returns "BadGateWay"
            res = repo.getWeatherDetails(request)
            assert(res is ResultState.HttpErrors.BadGateWay)
            message = (res as ResultState.HttpErrors.BadGateWay).exceptionMsg
            assertEquals(message, "BadGateWay")

            every { mockResponse.code() } returns 301
            every { mockResponse.message() } returns "ResourceRemoved"
            res = repo.getWeatherDetails(request)
            assert(res is ResultState.HttpErrors.ResourceRemoved)
            message = (res as ResultState.HttpErrors.ResourceRemoved).exceptionMsg
            assertEquals(message, "ResourceRemoved")

            every { mockResponse.code() } returns 302
            every { mockResponse.message() } returns "RemovedResourceFound"
            res = repo.getWeatherDetails(request)
            assert(res is ResultState.HttpErrors.RemovedResourceFound)
            message = (res as ResultState.HttpErrors.RemovedResourceFound).exceptionMsg
            assertEquals(message, "RemovedResourceFound")
        }

    }
}