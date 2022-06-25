package com.tusharkathuria.androidplayground.dagger.di

import com.tusharkathuria.androidplayground.dagger.thirdparty.Driver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DriverModule {
    companion object {
        @Singleton
        @Provides
        fun providesDriver(): Driver = Driver()
    }
}