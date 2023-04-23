package com.tusharkathuria.androidplayground.concurrency.threads

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import com.tusharkathuria.androidplayground.concurrency.APP_TAG
import java.io.PipedReader
import java.io.PipedWriter

@Composable
fun TextChangeProcessingUsingPipe() {
    val pipedWriter = remember { PipedWriter() }
    val pipedReader = remember { PipedReader() }
    val processingThread = remember {
        Thread {
            while (Thread.currentThread().isAlive) {
                var i: Int
                while (pipedReader.read().also { i = it } != -1) {
                    val c = i.toChar()
                    //ADD TEXT PROCESSING LOGIC HERE
                    Log.d(APP_TAG, "char = $c")
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        pipedWriter.connect(pipedReader)
        processingThread.start()
    }

    DisposableEffect(Unit) {
        onDispose {
            processingThread.interrupt()
            pipedReader.close()
            pipedWriter.close()
        }
    }

    var text by remember { mutableStateOf("") }
    val count = text.length
    TextField(value = text, onValueChange = {
        text = it
        if(it.length > count) {
            pipedWriter.write(it.takeLast(1))
        }
    })
}