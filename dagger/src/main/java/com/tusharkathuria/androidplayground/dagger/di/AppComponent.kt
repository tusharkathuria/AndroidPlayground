package com.tusharkathuria.androidplayground.dagger.di

import com.tusharkathuria.androidplayground.dagger.thirdparty.Driver
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(modules = [DriverModule::class])
public interface AppComponent {
//    fun getDriver(): Driver // Subcomponent can access driver without needing to be exposed
    fun activityComponent(dieselEngineModule: DieselEngineModule): ActivityComponent
}