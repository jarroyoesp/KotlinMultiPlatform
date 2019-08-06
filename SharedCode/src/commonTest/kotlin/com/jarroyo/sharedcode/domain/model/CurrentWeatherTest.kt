package com.jarroyo.sharedcode.domain.model

import com.jarroyo.sharedcode.TestUtils

..CurrentWeather
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

@UnstableDefault
class CurrentWeatherTest {

    @Test
    fun `parses json to CurrentWeather`() {
        val currentWeather = Json.parse(CurrentWeather.serializer(), TestUtils.jsonCurrentWeatherResponse)

        assertEquals("Andorra", currentWeather.name)
        assertEquals(6361450, currentWeather.id)
    }

}