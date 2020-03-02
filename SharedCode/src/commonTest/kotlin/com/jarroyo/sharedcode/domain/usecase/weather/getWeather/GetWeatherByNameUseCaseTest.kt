package com.jarroyo.sharedcode.domain.usecase.weather.getWeather

import com.jarroyo.sharedcode.TestUtils
import com.jarroyo.sharedcode.domain.Response
import com.jarroyo.sharedcode.domain.model.CurrentWeather
import com.jarroyo.sharedcode.domain.model.Location
import com.jarroyo.sharedcode.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class GetWeatherByNameUseCaseTest {

    private val repository = mockk<WeatherRepository>()
    private val getWeatherByNameUseCase = GetWeatherByNameUseCase(repository)

    @Test
    fun `returns_current_weather_from_WeatherRepository_if_no_error`() {
        val location = mockk<Location>()

        val currentWeather =   Json.parse(CurrentWeather.serializer(), TestUtils.jsonCurrentWeatherResponse)
        coEvery { repository.getWeather(location) } returns Response.Success(currentWeather)

        val request = GetWeatherByNameRequest(location)

        // This actually doesn't work, but makes this test compile. Testing suspending functions is currently
        // not possible in common modules
        suspend {
            val response = getWeatherByNameUseCase.execute(request)
            assertEquals(response, Response.Success(currentWeather))
        }
    }


    @Test
    fun `returns_error_from_WeatherRepository_if_erro_getting_weather`() {
        val location = mockk<Location>()

        val currentWeather =   Json.parse(CurrentWeather.serializer(), TestUtils.jsonCurrentWeatherResponse)
        coEvery { repository.getWeather(location) } returns Response.Error(IllegalArgumentException())

        val request = GetWeatherByNameRequest(location)

        // This actually doesn't work, but makes this test compile. Testing suspending functions is currently
        // not possible in common modules
        suspend {
            val response = getWeatherByNameUseCase.execute(request)
            assertEquals(response, Response.Error(IllegalArgumentException()))
        }
    }

}