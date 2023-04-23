package com.tusharkathuria.androidplayground.concurrency.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tusharkathuria.androidplayground.concurrency.PlaygroundType
import com.tusharkathuria.androidplayground.concurrency.threads.*

@Composable
fun PlaygroundScreen(type: PlaygroundType, modifier: Modifier = Modifier) {
    when(type) {
        PlaygroundType.RaceConditionNonDeterministic -> DefaultContent(
            RaceConditionNonDeterministic()
        )
        PlaygroundType.RaceConditionDeterministic -> DefaultContent(
            RaceConditionDeterministic()
        )
        PlaygroundType.ProducerWorkerConsumerWorker -> DefaultContent(
            ProducerWorkerConsumerWorker()
        )
        PlaygroundType.AndroidTextChangeProcessingUsingPipe -> TextChangeProcessingUsingPipe()
        PlaygroundType.ProducerConsumerBlockingQueue -> DefaultContent(
            ProducerConsumerBlockingQueue()
        )
        PlaygroundType.AndroidOnClickLongTask -> OnClickLongTask()
        PlaygroundType.ConsumeAndQuitThread -> DefaultContent(executor = ConsumeAndQuit())
    }
}