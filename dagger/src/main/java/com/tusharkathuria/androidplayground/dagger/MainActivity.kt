package com.tusharkathuria.androidplayground.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tusharkathuria.androidplayground.dagger.car.Car
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var car1: Car

    @Inject
    lateinit var car2: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as DaggerApp)
            .appComponent
            .activityComponentBuilder()
            .horsePower(120)
            .engineCapacity(1500)
            .build()
            .inject(this)

        car1.drive()
        car2.drive()
    }
}