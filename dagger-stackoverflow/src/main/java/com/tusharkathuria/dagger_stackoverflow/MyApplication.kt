package com.tusharkathuria.dagger_stackoverflow


import android.app.Application
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.AppCompositionRoot

class MyApplication: Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        appCompositionRoot = AppCompositionRoot()
        super.onCreate()
    }
}