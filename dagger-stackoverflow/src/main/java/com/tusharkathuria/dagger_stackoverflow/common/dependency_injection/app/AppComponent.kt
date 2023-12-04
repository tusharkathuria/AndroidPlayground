package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.app

import android.app.Application
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityModule
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.service.ServiceComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.service.ServiceModule
import com.tusharkathuria.dagger_stackoverflow.networking.StackoverflowApi
import dagger.Component
import javax.inject.Singleton

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

    fun newServiceComponent(serviceModule: ServiceModule): ServiceComponent
}