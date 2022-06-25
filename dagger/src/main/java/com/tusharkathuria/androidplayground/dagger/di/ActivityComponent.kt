package com.tusharkathuria.androidplayground.dagger.di

import com.tusharkathuria.androidplayground.dagger.MainActivity
import com.tusharkathuria.androidplayground.dagger.car.Car
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import javax.inject.Named

@PerActivity
@Subcomponent(modules = [WheelsModule::class, PetrolEngineModule::class])
interface ActivityComponent {
    fun getCar(): Car

    fun inject(activity: MainActivity)

    // Factory is sometimes better than builder as it provides compile time check that all required params are passed
    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance @Named("horse power") horsePower: Int,
            @BindsInstance @Named("engine capacity") engineCapacity: Int
        ): ActivityComponent
    }
}