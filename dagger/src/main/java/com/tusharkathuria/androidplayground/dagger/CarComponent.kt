package com.tusharkathuria.androidplayground.dagger

import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [WheelsModule::class, DieselEngineModule::class])
interface CarComponent {
    fun getCar(): Car

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun horsePower(@Named("horse power") horsePower: Int): Builder

        @BindsInstance
        fun engineCapacity(@Named("engine capacity") engineCapacity: Int): Builder

        fun build(): CarComponent
    }
}