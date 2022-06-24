package com.tusharkathuria.androidplayground.dagger

import dagger.Component

@Component(modules = [WheelsModule::class, DieselEngineModule::class])
interface CarComponent {
    fun getCar(): Car

    fun inject(activity: MainActivity)
}