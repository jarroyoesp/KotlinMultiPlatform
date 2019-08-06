package com.jarroyo.sharedcode.source.repository

import com.jarroyo.sharedcode.LocationFactoryTest
import com.jarroyo.sharedcode.source.disk.DatabaseCreator
import com.jarroyo.sharedcode.source.disk.DbArgs
import com.jarroyo.kotlinmultiplatform.domain.Response

..Location
import com.jarroyo.kotlinmultiplatform.repository.LocationRepository
import com.jarroyo.kotlinmultiplatform.source.disk.dao.LocationDao
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals

class LocationRepositoryTest {

    private val databaseCreator = mockk<DatabaseCreator>()
    private val locationDao = mockk<LocationDao>()
    private val dbArgs = mockk<DbArgs>()
    private val locationRepository = LocationRepository(dbArgs)

    @Test
    fun `returns Location List from Database`() {

        // This actually doesn't work, but makes this test compile. Testing suspending functions is currently
        // not possible in common modules
        suspend {
            val location = mockk<Location>()

            coEvery { locationDao.select() } returns LocationFactoryTest.createLocationModelListTest()

            val response = locationRepository.getLocationList()
            val responseData = response as Response.Success

            assertEquals(responseData.data, LocationFactoryTest.createLocationModelListTest())

        }
    }

}