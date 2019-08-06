package com.jarroyo.sharedcode.presentation.profile

import com.jarroyo.sharedcode.domain.usecase.location.getLocationList.GetLocationMPPListUseCase
import com.jarroyo.sharedcode.domain.usecase.location.saveLocation.SaveLocationUseCase
import com.jarroyo.sharedcode.domain.usecase.weather.getWeather.GetWeatherByNameUseCase
import com.jarroyo.sharedcode.presentation.ProfilePresenter
import com.jarroyo.sharedcode.presentation.ProfileView
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class ProfilePresenterTest {

    private val getLocationMPPListUseCase = mockk<GetLocationMPPListUseCase>()
    private val getCurrentWeatherByNameUseCase = mockk<GetWeatherByNameUseCase>()
    private val saveLocationUseCase = mockk<SaveLocationUseCase>()
    private val presenter = ProfilePresenter(getLocationMPPListUseCase, getCurrentWeatherByNameUseCase, saveLocationUseCase)
    private val view = mockk<ProfileView>(relaxUnitFun = true)

    @Test
    fun `shows loading when getLocationList is called`() {
        presenter.attachView(view)
        presenter.getLocationList()

        verify { view.showHideLoading(true) }
    }

}