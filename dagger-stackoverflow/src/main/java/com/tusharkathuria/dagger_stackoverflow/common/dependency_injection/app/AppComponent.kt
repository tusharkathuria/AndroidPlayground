package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.app

import android.app.Application
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import dagger.Component
import javax.inject.Singleton

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun application(): Application
    fun stackOverflowApi(): StackoverflowApi
}