package com.tusharkathuria.dagger_stackoverflow.common

import android.app.Service
import com.tusharkathuria.dagger_stackoverflow.MyApplication
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity.ActivityModule
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.service.ServiceComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.service.ServiceModule

abstract class BaseService: Service() {
    val serviceComponent: ServiceComponent by lazy {
        (application as MyApplication).appComponent.newServiceComponent(ServiceModule(this))
    }
}