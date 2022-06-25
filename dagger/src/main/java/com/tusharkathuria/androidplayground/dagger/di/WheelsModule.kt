package com.tusharkathuria.androidplayground.dagger.di

import com.tusharkathuria.androidplayground.dagger.thirdparty.Rims
import com.tusharkathuria.androidplayground.dagger.thirdparty.Tires
import com.tusharkathuria.androidplayground.dagger.thirdparty.Wheels
import dagger.Module
import dagger.Provides

@Module
class WheelsModule {

    companion object {
        @Provides
        fun providesRims(): Rims = Rims()

        @Provides
        fun providesTires(): Tires = Tires().apply { inflate() }

        @Provides
        fun providesWheels(rims: Rims, tires: Tires): Wheels = Wheels(rims, tires)
    }
}