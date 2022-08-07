package com.tusharkathuria.androidplayground.concurrency.threads

import android.util.Log
import com.tusharkathuria.androidplayground.concurrency.APP_TAG
import com.tusharkathuria.androidplayground.concurrency.IDefaultExecutor
import java.util.*

class ThreadsProducerWorkerConsumerWorker : IDefaultExecutor {
    private val list = LinkedList<Int>()
    private val limit = 10;
    private val listLock = Object()

    private fun produce() {
        var value = 0;
        while (true) {
            synchronized(listLock) {
                while (list.size == limit) {
                    listLock.wait() // Give up execution
                }
                Thread.sleep(2000)
                value++
                list.add(value)
                Log.i(APP_TAG, "Producing $value")
                Log.i(APP_TAG, "Updated list: $list")
                listLock.notify() // Notify waiting threads that they can resume
            }
        }
    }

    private fun consume() {
        while (true) {
            synchronized(listLock) {
                while (list.size == 0) {
                    listLock.wait()
                }
                Thread.sleep(2000)
                val value = list.removeFirst()
                Log.i(APP_TAG, "Consuming $value")
                Log.i(APP_TAG, "Updated list: $list")
                listLock.notify()
            }
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