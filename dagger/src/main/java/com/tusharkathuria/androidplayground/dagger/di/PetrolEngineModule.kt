package com.tusharkathuria.androidplayground.dagger.di

import com.tusharkathuria.androidplayground.dagger.car.Engine
import com.tusharkathuria.androidplayground.dagger.car.PetrolEngine
import dagger.Binds
import dagger.Module

@Module
abstract class PetrolEngineModule {
    @Binds
    abstract fun bindsEngine(petrolEngine: PetrolEngine): Engine
}