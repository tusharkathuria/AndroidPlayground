package com.tusharkathuria.androidplayground.dagger

import dagger.Component

@Component(modules = [WheelsModule::class])
interface CarComponent {
    fun getCar(): Car

    fun inject(activity: MainActivity)
}