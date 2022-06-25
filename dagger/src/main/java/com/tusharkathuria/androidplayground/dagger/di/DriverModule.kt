package com.tusharkathuria.androidplayground.dagger.di

import com.tusharkathuria.androidplayground.dagger.thirdparty.Driver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DriverModule(val name: String) {
    @Singleton
    @Provides
    fun providesDriver(): Driver = Driver(name)
}