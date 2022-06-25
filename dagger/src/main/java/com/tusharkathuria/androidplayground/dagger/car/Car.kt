package com.tusharkathuria.androidplayground.dagger.car

import android.util.Log
import com.tusharkathuria.androidplayground.dagger.di.PerActivity
import com.tusharkathuria.androidplayground.dagger.thirdparty.Driver
import com.tusharkathuria.androidplayground.dagger.thirdparty.Wheels
import javax.inject.Inject

@PerActivity
class Car @Inject constructor(val driver: Driver, wheels: Wheels) {
    @Inject
    lateinit var engine: Engine

    companion object {
        const val TAG = "Car"
    }

    fun drive() {
        engine.start()
        Log.d(TAG, "$driver [${driver.name}] driving $this")
    }

    @Inject
    fun enableRemote(remote: Remote) {
        remote.setListener(this)
    }
}