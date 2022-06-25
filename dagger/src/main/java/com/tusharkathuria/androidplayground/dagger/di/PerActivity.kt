package com.tusharkathuria.androidplayground.dagger.di

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class PerActivity()

// All scope annotations by default behave same as Singleton annotation.
// They tell dagger to reuse object in same component