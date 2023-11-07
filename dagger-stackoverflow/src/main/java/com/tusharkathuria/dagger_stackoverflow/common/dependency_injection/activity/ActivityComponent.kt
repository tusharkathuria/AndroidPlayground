package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity

import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.PresentationComponent
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.PresentationModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}