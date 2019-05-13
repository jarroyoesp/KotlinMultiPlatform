package com.jarroyo.firstkotlinmultiplatform.app.di.viewModel

import androidx.lifecycle.ViewModel
import com.jarroyo.firstkotlinmultiplatform.ui.viewModel.location.LocationViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    abstract fun bindCurrentWeatherViewModel(viewModel: LocationViewModel): ViewModel
}