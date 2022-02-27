package com.tusharkathuria.androidplayground.homenav

import androidx.test.platform.app.InstrumentationRegistry

fun stringResource(id: Int): String = InstrumentationRegistry.getInstrumentation().targetContext.getString(id)