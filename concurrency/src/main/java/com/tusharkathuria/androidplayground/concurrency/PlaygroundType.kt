package com.tusharkathuria.androidplayground.concurrency

enum class PlaygroundType {
    RaceConditionNonDeterministic,
    RaceConditionDeterministic,
    ProducerWorkerConsumerWorker,
    AndroidTextChangeProcessingUsingPipe,
    ProducerConsumerBlockingQueue,
    AndroidOnClickLongTask,
    ConsumeAndQuitThread
}