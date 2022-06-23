package com.tusharkathuria.androidplayground.dagger

import android.util.Log
import javax.inject.Inject

class Car @Inject constructor(engine: Engine, wheels: Wheels) {
    companion object {
        const val TAG = "Car"
    }

    fun drive() {
        Log.d(TAG, "driving..")
    }
}