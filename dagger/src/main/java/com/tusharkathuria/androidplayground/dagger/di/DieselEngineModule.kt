package com.tusharkathuria.androidplayground.dagger.di

import com.tusharkathuria.androidplayground.dagger.car.DieselEngine
import com.tusharkathuria.androidplayground.dagger.car.Engine
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DieselEngineModule(val horsePower: Int) {
    @Provides
    @Named("horse power")
    fun providesHorsePower(): Int = horsePower

    @Provides
    fun providesEngine(dieselEngine: DieselEngine): Engine = dieselEngine
}