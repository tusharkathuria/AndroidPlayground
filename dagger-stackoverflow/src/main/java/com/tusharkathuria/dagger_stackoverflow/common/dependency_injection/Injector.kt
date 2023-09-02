package com.tusharkathuria.dagger_stackoverflow.common.dependency_injection

import com.tusharkathuria.dagger_stackoverflow.screens.common.ScreensNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.common.UIFactory
import com.tusharkathuria.dagger_stackoverflow.screens.common.dialogs.DialogsNavigator
import com.tusharkathuria.dagger_stackoverflow.screens.questiondetails.FetchQuestionDetailUseCase
import com.tusharkathuria.dagger_stackoverflow.screens.questionlist.FetchQuestionsUseCase
import java.lang.reflect.Field

class Injector(private val compositionRoot: PresentationCompositionRoot) {

    fun inject(client: Any) {
        for (field in getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field)
            }
        }
    }

    private fun getAllFields(client: Any): Array<out Field> {
        val clientClass = client::class.java
        return clientClass.declaredFields
    }

    private fun isAnnotatedForInjection(field: Field): Boolean {
        val fieldAnnotations = field.annotations
        for (annotation in fieldAnnotations) {
            if (annotation is Service) {
                return true
            }
        }
        return false
    }

    private fun injectField(client: Any, field: Field) {
        val isAccessibleInitially = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceForClass(field.type))
        field.isAccessible = isAccessibleInitially
    }

    private fun getServiceForClass(type: Class<*>): Any {
        when (type) {
            DialogsNavigator::class.java -> {
                return compositionRoot.dialogsNavigator
            }
            ScreensNavigator::class.java -> {
                return compositionRoot.screensNavigator
            }
            FetchQuestionsUseCase::class.java -> {
                return compositionRoot.fetchQuestionsUseCase
            }
            FetchQuestionDetailUseCase::class.java -> {
                return compositionRoot.fetchQuestionDetailUseCase
            }
            UIFactory::class.java -> {
                return compositionRoot.uiFactory
            }
            else -> {
                throw Exception("unsupported service type: $type")
            }
        }
    }


}