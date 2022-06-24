package com.tusharkathuria.androidplayground.dagger

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class DieselEngineModule(val horsePower: Int) {
    @Provides
    fun provideEngine(): Engine = DieselEngine(horsePower)
}