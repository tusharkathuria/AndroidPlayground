package com.tusharkathuria.dagger_stackoverflow


import android.app.Application
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.app.AppComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.app.AppModule
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.app.DaggerAppComponent

class MyApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}