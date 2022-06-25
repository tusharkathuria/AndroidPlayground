package com.tusharkathuria.androidplayground.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tusharkathuria.androidplayground.dagger.car.Car
import com.tusharkathuria.androidplayground.dagger.di.DaggerActivityComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var car1: Car

    @Inject
    lateinit var car2: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerActivityComponent.builder()
            .horsePower(120)
            .engineCapacity(1500)
            .appComponent((application as DaggerApp).appComponent)
            .build()
            .inject(this)

        car1.drive()
        car2.drive()
    }
}