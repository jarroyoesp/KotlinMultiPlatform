package com.jarroyo.firstkotlinmultiplatform.presentation.profile

import com.jarroyo.firstkotlinmultiplatform.domain.usecase.location.saveLocation.SaveLocationUseCase
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeather.GetWeatherByNameUseCase
import com.jarroyo.firstkotlinmultiplatform.presentation.ProfilePresenter
import com.jarroyo.firstkotlinmultiplatform.presentation.ProfileView
import com.jarroyo.kotlinmultiplatform.domain.usecase.location.getLocationList.GetLocationMPPListUseCase
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