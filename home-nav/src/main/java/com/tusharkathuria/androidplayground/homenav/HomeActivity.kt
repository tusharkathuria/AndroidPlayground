package com.tusharkathuria.androidplayground.homenav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tusharkathuria.androidplayground.commonui.theme.AndroidPlaygroundTheme
import com.tusharkathuria.androidplayground.homenav.ui.Home

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPlaygroundTheme {
                Home()
            }
        }
    }
}