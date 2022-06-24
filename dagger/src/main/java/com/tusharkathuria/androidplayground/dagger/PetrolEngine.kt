package com.tusharkathuria.androidplayground.dagger

import android.util.Log
import javax.inject.Inject

class PetrolEngine @Inject constructor() : Engine {
    override fun start() {
        Log.d("Car", "Petrol engine started")
    }
}