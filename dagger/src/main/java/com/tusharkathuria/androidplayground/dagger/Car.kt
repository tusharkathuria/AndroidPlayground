package com.tusharkathuria.androidplayground.dagger

import android.util.Log
import com.tusharkathuria.androidplayground.dagger.thirdparty.Wheels
import javax.inject.Inject

class Car @Inject constructor(val driver: Driver, wheels: Wheels) {
    @Inject
    lateinit var engine: Engine

    companion object {
        const val TAG = "Car"
    }

    fun drive() {
        engine.start()
        Log.d(TAG, "$driver driving $this")
    }

    @Inject
    fun enableRemote(remote: Remote) {
        remote.setListener(this)
    }
}