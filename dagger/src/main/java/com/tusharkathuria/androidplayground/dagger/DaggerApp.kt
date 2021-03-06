package com.tusharkathuria.androidplayground.dagger

import android.app.Application
import com.tusharkathuria.androidplayground.dagger.di.ActivityComponent
import com.tusharkathuria.androidplayground.dagger.di.AppComponent
import com.tusharkathuria.androidplayground.dagger.di.DaggerAppComponent
import com.tusharkathuria.androidplayground.dagger.di.DriverModule

class DaggerApp: Application() {
    lateinit var appComponent: AppComponent
    private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(DriverModule("Tushar"))
    }
}