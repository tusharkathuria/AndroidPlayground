package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.service

import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.PresentationComponent
import dagger.Subcomponent

@Subcomponent(modules = [ServiceModule::class])
interface ServiceComponent {

}