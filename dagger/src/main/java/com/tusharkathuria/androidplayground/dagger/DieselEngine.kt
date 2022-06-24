package com.tusharkathuria.androidplayground.dagger

import android.util.Log
import javax.inject.Inject

class DieselEngine @Inject constructor() : Engine {
    override fun start() {
        Log.d("Car", "Diesel engine started")
    }
}