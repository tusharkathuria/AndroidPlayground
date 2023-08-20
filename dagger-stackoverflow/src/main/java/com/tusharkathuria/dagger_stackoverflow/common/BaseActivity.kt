package com.tusharkathuria.dagger_stackoverflow.common

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.tusharkathuria.dagger_stackoverflow.MyApplication

open class BaseActivity: AppCompatActivity() {
    protected val compositionRoot get() = (application as MyApplication).appCompositionRoot
}