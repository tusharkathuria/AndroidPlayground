package com.tusharkathuria.androidplayground.concurrency.threads

import android.util.Log
import com.tusharkathuria.androidplayground.concurrency.APP_TAG
import com.tusharkathuria.androidplayground.concurrency.IDefaultExecutor
import java.util.*
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingDeque

class ProducerConsumerBlockingQueue : IDefaultExecutor {
    private val limit = 10
    private val list = LinkedBlockingDeque<Int>(limit)

    private fun produce() {
        var value = 0
        while (true) {
            value++
            list.put(value)
            // Note: Following two logs are not synchronized so output may not be in accurate order
            Log.i(APP_TAG, "Producing $value")
            Log.i(APP_TAG, "Updated list: $list")
            Thread.sleep((Math.random()*2000).toLong())
        }
    }

    private fun consume() {
        while (true) {
            val value = list.take()
            // Note: Following two logs are not synchronized so output may not be in accurate order
            Log.i(APP_TAG, "Consuming $value")
            Log.i(APP_TAG, "Updated list: $list")
            Thread.sleep((Math.random()*20000).toLong())
        }
    }

    override fun execute() {
        val thread1 = Thread {
            produce()
        }

        val thread2 = Thread {
            consume()
        }

        thread1.start()
        thread2.start()
    }
}