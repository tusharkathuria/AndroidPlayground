package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.activity

import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.common.dependency_injection.presentation.PresentationComponent
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent(): PresentationComponent

    @Subcomponent.Builder
    interface Builder {
        // By default dagger builder binds modules. But in this case we want to bind instance passed so annotation is needed
        @BindsInstance fun activity(activity: AppCompatActivity): Builder
        fun activityModule(activityModule: ActivityModule): Builder
        fun build(): ActivityComponent
    }
}