package com.tusharkathuria.androidplayground.dagger

import dagger.Component

@Component
interface CarComponent {
    fun getCar(): Car

    fun inject(activity: MainActivity)
}