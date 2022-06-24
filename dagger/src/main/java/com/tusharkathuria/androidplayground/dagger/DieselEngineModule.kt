package com.tusharkathuria.androidplayground.dagger

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DieselEngineModule {
    @Binds
    abstract fun provideEngine(dieselEngine: DieselEngine): Engine
}