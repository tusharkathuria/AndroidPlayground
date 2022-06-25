package com.tusharkathuria.androidplayground.dagger.di

import com.tusharkathuria.androidplayground.dagger.car.DieselEngine
import com.tusharkathuria.androidplayground.dagger.car.Engine
import dagger.Binds
import dagger.Module

@Module
abstract class DieselEngineModule {
    @Binds
    abstract fun bindsEngine(dieselEngine: DieselEngine): Engine
}