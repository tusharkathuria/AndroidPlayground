package com.tusharkathuria.androidplayground.dagger

import android.util.Log
import javax.inject.Inject
import javax.inject.Named

class DieselEngine @Inject constructor(@Named("horse power") val horsePower: Int) : Engine {
    override fun start() {
        Log.d("Car", "Diesel engine started. Horsepower: $horsePower")
    }
}