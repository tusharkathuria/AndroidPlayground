package com.tusharkathuria.androidplayground.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var car: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerCarComponent.builder().horsePower(150).engineCapacity(1400).build().inject(this)
        car.drive()
    }
}