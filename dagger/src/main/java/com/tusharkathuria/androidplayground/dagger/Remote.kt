package com.tusharkathuria.androidplayground.dagger

import android.util.Log
import javax.inject.Inject

class Remote @Inject constructor() {
    fun setListener(car: Car) {
        Log.d("Remote", "Car connected..!")
    }
}