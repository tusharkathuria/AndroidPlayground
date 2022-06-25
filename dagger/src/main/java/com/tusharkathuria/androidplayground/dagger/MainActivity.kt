package com.tusharkathuria.androidplayground.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tusharkathuria.androidplayground.dagger.car.Car
import com.tusharkathuria.androidplayground.dagger.di.DieselEngineModule
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
            .activityComponent(DieselEngineModule(120))
            .inject(this)

        car1.drive()
        car2.drive()
    }
}