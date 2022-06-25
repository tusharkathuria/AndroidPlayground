package com.tusharkathuria.androidplayground.dagger.di

import com.tusharkathuria.androidplayground.dagger.MainActivity
import com.tusharkathuria.androidplayground.dagger.car.Car
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [WheelsModule::class, DieselEngineModule::class])
interface ActivityComponent {
    fun getCar(): Car

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun horsePower(@Named("horse power") horsePower: Int): Builder

        @BindsInstance
        fun engineCapacity(@Named("engine capacity") engineCapacity: Int): Builder

        fun appComponent(component: AppComponent): Builder

        fun build(): ActivityComponent
    }
}