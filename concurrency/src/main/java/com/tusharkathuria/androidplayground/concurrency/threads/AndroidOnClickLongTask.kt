package com.tusharkathuria.androidplayground.concurrency.threads

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.tusharkathuria.androidplayground.concurrency.APP_TAG

private class ClickProcessingThread: Thread() {
    companion object {
        val UIEvent = 0
    }

    var uiEventHandler: Handler? = null

    override fun run() {
        Looper.prepare()
        uiEventHandler = Handler {
            if(it.what == UIEvent) {
                Log.d(APP_TAG, "Received message")
                return@Handler true
            }
            return@Handler false
        }
        Looper.loop()
    }
}

@Composable
fun OnClickLongTask() {
    val clickProcessingThread = remember {
        ClickProcessingThread()
    }

    LaunchedEffect(Unit) {
        clickProcessingThread.start()
    }

    DisposableEffect(Unit) {
        onDispose {
            clickProcessingThread.uiEventHandler?.looper?.quit()
        }
    }

    Button(onClick = {
        clickProcessingThread.uiEventHandler?.let {
            it.sendMessage(it.obtainMessage(ClickProcessingThread.UIEvent))
        }
    }, Modifier.wrapContentSize()) {
        Text(text = "Click me !")
    }
}