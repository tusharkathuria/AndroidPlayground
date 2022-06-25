package com.tusharkathuria.androidplayground.dagger.thirdparty

import android.util.Log

// We don't own so can't use inject
class Tires {
    fun inflate() {
        Log.d("Car", "Inflating")
    }
}