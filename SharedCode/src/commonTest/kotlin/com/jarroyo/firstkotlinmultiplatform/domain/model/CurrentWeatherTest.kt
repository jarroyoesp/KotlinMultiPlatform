package com.jarroyo.firstkotlinmultiplatform.domain.model

import com.jarroyo.kotlinmultiplatform.domain.model.CurrentWeather
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

@UnstableDefault
class CurrentWeatherTest {

    private val json = "{\n" +
            "  \"coord\": {\n" +
            "    \"lon\": -0.44,\n" +
            "    \"lat\": 40.98\n" +
            "  },\n" +
            "  \"weather\": [\n" +
            "    {\n" +
            "      \"id\": 800,\n" +
            "      \"main\": \"Clear\",\n" +
            "      \"description\": \"clear sky\",\n" +
            "      \"icon\": \"01d\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"base\": \"stations\",\n" +
            "  \"main\": {\n" +
            "    \"temp\": 27.21,\n" +
            "    \"pressure\": 996,\n" +
            "    \"humidity\": 56,\n" +
            "    \"temp_min\": 26.11,\n" +
            "    \"temp_max\": 28.33\n" +
            "  },\n" +
            "  \"wind\": {\n" +
            "    \"speed\": 1.34,\n" +
            "    \"deg\": 357,\n" +
            "    \"gust\": 2.68\n" +
            "  },\n" +
            "  \"clouds\": {\n" +
            "    \"all\": 0\n" +
            "  },\n" +
            "  \"dt\": 1561559219,\n" +
            "  \"sys\": {\n" +
            "    \"type\": 3,\n" +
            "    \"id\": 2020190,\n" +
            "    \"message\": 0.0082,\n" +
            "    \"country\": \"ES\",\n" +
            "    \"sunrise\": 1561523464,\n" +
            "    \"sunset\": 1561577869\n" +
            "  },\n" +
            "  \"timezone\": 7200,\n" +
            "  \"id\": 6361450,\n" +
            "  \"name\": \"Andorra\",\n" +
            "  \"cod\": 200\n" +
            "}"

    @Test
    fun `parses json to CurrentWeather`() {
        val currentWeather = Json.parse(CurrentWeather.serializer(), json)

        assertEquals("Andorra", currentWeather.name)
        assertEquals(6361450, currentWeather.id)
    }

}