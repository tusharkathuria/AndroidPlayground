package com.tusharkathuria.androidplayground.concurrency.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.tusharkathuria.androidplayground.concurrency.APP_TAG
import com.tusharkathuria.androidplayground.concurrency.threads.ThreadsProducerWorkerConsumerWorker
import com.tusharkathuria.androidplayground.concurrency.theme.AndroidPlaygroundTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(APP_TAG, "Activity started")
        setContent {
            AndroidPlaygroundTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppUI()
                }
            }
        }
    }
}