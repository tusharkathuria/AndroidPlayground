package com.tusharkathuria.androidplayground.dagger

import android.util.Log
import javax.inject.Inject

class DieselEngine(val horsePower: Int) : Engine {
    override fun start() {
        Log.d("Car", "Diesel engine started. Horsepower: $horsePower")
    }
}