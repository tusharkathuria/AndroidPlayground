package com.tusharkathuria.androidplayground.dagger

import android.util.Log
import com.tusharkathuria.androidplayground.dagger.thirdparty.Wheels
import javax.inject.Inject

class Car @Inject constructor(wheels: Wheels) {
    @Inject
    lateinit var engine: Engine

    companion object {
        const val TAG = "Car"
    }

    fun drive() {
        engine.start()
        Log.d(TAG, "driving..")
    }

    @Inject
    fun enableRemote(remote: Remote) {
        remote.setListener(this)
    }
}