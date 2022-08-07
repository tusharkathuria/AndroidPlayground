package com.tusharkathuria.androidplayground.concurrency.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tusharkathuria.androidplayground.concurrency.PlaygroundType
import com.tusharkathuria.androidplayground.concurrency.threads.ThreadsProducerWorkerConsumerWorker
import com.tusharkathuria.androidplayground.concurrency.threads.ThreadsRaceConditionDeterministic
import com.tusharkathuria.androidplayground.concurrency.threads.ThreadsRaceConditionNonDeterministic

@Composable
fun PlaygroundScreen(type: PlaygroundType, modifier: Modifier = Modifier) {
    when(type) {
        PlaygroundType.ThreadsRaceConditionNonDeterministic -> DefaultContent(
            ThreadsRaceConditionNonDeterministic()
        )
        PlaygroundType.ThreadsRaceConditionDeterministic -> DefaultContent(
            ThreadsRaceConditionDeterministic()
        )
        PlaygroundType.ThreadsProducerWorkerConsumerWorker -> DefaultContent(
            ThreadsProducerWorkerConsumerWorker()
        )
    }
}