package com.jarroyo.firstkotlinmultiplatform.di

import com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeather.GetWeatherByNameUseCase
import com.jarroyo.firstkotlinmultiplatform.domain.usecase.weather.getWeatherList.GetWeatherListUseCase
import com.jarroyo.kotlinmultiplatform.repository.WeatherRepository
import com.jarroyo.kotlinmultiplatform.source.network.WeatherApi


object InjectorCommon {


    private val weatherApi: WeatherApi by lazy { WeatherApi() }


    private val weatherRepository: WeatherRepository by lazy {
        WeatherRepository(weatherApi)
    }

    fun provideGetWeatherListUseCase(): GetWeatherListUseCase {
        return GetWeatherListUseCase(weatherRepository)
    }

    fun provideGetWeatherUseCase(): GetWeatherByNameUseCase {
        return GetWeatherByNameUseCase(weatherRepository)
    }


    /*val ktorNetworkService by lazy {
        KtorNetworkService("192.168.0.11", 8080, HttpClient { install(JsonFeature) })
    }

    private val userLoginService: UserLoginService by lazy { ktorNetworkService }


    private val loginUserUseCase: LoginUserUseCase by lazy { LoginUserUseCase(userLoginRepository, userLoginService) }
    private val getLoginState: GetLoginStateUseCase by lazy { GetLoginStateUseCase(userLoginRepository) }


    fun provideUserLoginPresenter(view: UserLoginView): UserLoginPresenter =
        UserLoginPresenterImpl(loginUserUseCase, getLoginState, view)


    private val signupUserUseCase: SignUpUseCase by lazy { SignUpUseCase(userLoginRepository, userLoginService) }

    fun provideSignupPresenter(view: UserSignupView): UserSignupPresenter =
        UserSignupPresenterImpl(signupUserUseCase, view)


    private val timelineRepository: TimelineRepository by lazy { TimelineRepositoryImpl(ktorNetworkService) }
    private val getTimelineUsecase: GetTimelineUsecase by lazy {
        GetTimelineUsecase(userLoginRepository, timelineRepository)
    }
    private val deletePostUsecase: DeletePostFromTimelineUsecase by lazy {
        DeletePostFromTimelineUsecase(userLoginRepository, timelineRepository)
    }

    private val postToTimelineUsecase: PostToTimelineUsecase by lazy {
        PostToTimelineUsecase(userLoginRepository, timelineRepository)
    }

    private val logoutUseCase: LogoutUseCase by lazy { LogoutUseCase(userLoginRepository, userLoginService) }

    fun provideTimelinePresenter(view: TimelineView): TimelinePresenter =
        TimelinePresenterImpl(view, getTimelineUsecase, deletePostUsecase, postToTimelineUsecase, logoutUseCase)


    fun provideLoadingScreenPresenter(view: LoadingScreenView) : LoadingScreenPresenter =
            LoadingScreenPresenterImpl(view, getLoginState)*/
}
