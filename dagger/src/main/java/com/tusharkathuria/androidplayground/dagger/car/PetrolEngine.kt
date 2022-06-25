package com.tusharkathuria.androidplayground.dagger.car

import android.util.Log
import javax.inject.Inject
import javax.inject.Named

class PetrolEngine @Inject constructor(@Named("horse power") val horsePower: Int, @Named("engine capacity") var engineCapacity: Int) :
    Engine {
    override fun start() {
        Log.d("Car", "Petrol engine started. Horse power: $horsePower. Engine cap: $engineCapacity")
    }
}